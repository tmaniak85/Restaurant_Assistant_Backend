package com.edu.assistant.service;

import com.edu.assistant.dao.UserDao;
import com.edu.assistant.exception.*;
import com.edu.assistant.model.User;
import com.edu.assistant.model.UserAuthority;
import com.edu.assistant.dto.UserCredentialsDto;
import com.edu.assistant.dto.UserCredentialsPasswordDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public UserDetails register(UserCredentialsDto introducedUser) throws UsernameExistException {
        if(this.userDao.existsUserByUsername(introducedUser.getUsername())) {
            throw new UsernameExistException("C003-User with name exist");
        } else {
            User user = new User();
            user.setUsername(introducedUser.getUsername());
            user.setPassword(passwordEncoder.encode(introducedUser.getPassword()));
            user.setFirstName(introducedUser.getFirstName());
            user.setLastName(introducedUser.getLastName());
            user.setUserAuthority(introducedUser.getUserAuthority());
            user.setActive(true);
            return userDao.save(user);
        }
    }

    public void registerAdminTestUser() {
        User user = new User();
        user.setUsername("ADMIN");
        user.setPassword("ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstName("ADMIN");
        user.setLastName("ADMIN");
        user.setActive(true);
        user.setUserAuthority(UserAuthority.ADMIN);
        userDao.save(user);
    }

    public void changePassword(UserCredentialsPasswordDto userCredentialsPasswordDto, long id) {
        User user = (User) findById(id);
            user.setPassword(passwordEncoder.encode(userCredentialsPasswordDto.getPassword()));
            userDao.save(user);
    }

    public UserDetails findById(Long id) {
        try {
            return userDao.getById(id);
        } catch (Exception e) {
            throw new NotFoundException("User not found");
        }
    }

    public List<User> showActiveUsers() {
        return userDao.findAllByIsActiveIs(true);
    }

    public List<User> showActiveWaiters() {
        return userDao.findAllByIsActiveIsAndUserAuthorityEquals(true, UserAuthority.WAITER);
    }


    public List<User> showAvailableWaiters() {
        return userDao.findAllByAvailableIsAndUserAuthorityEquals(true, UserAuthority.WAITER);
    }

    public User login(String username, String password) throws BadCredentialsException {

            User user = userDao.findByUsername(username);
            if(user == null || password == null || !passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("C003-Wrong Credentials");
            }
            user.setToken(UUID.randomUUID().toString());
            user.setAvailable(true);
            return userDao.save(user);
    }

    public void logout(String username) {
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new NotFoundException(username);
        }
        user.setAvailable(false);
        user.setToken(null);
        userDao.save(user);
    }

    public void setAsNonActive(Long id, String authorization)
            throws TableInUserExistException, ForbiddenDeleteException {
            User user = userDao.getById(id);
            String token = authorization;
            if(user.getToken() != null && user.getToken().equals(token)) {
                throw new ForbiddenDeleteException("C008-You cannnot delete yourself");
            }
            if(user.getTable().size() == 0) {
                user.setActive(false);
                user.setPassword(null);
                user.setAvailable(false);
                user.setToken(null);
                userDao.save(user);
            } else {
                throw new TableInUserExistException("C007-User has active table fields");
            }
    }

    public void deleteAdminUser(Long id, String authorization) throws ForbiddenDeleteException {
        User user = userDao.getById(id);
        String token = authorization;
        if(user.getToken() != null && user.getToken().equals(token)) {
            throw new ForbiddenDeleteException("C009-You cannnot delete yourself");
        }
        if(user.getUsername().equals("ADMIN")) {
            userDao.delete(user);
        } else {
            throw new NotFoundException("Delete is not possible except of ADMIN");
        }
    }

    public User loadUserByUsername(String username) {
        User user = userDao.findByUsername(username);
        if(user == null && !username.equals("ADMIN")) {
            throw new NotFoundException(username);
        }
        return user;
    }

    public UserDetails findUserByToken(String token) {
        token = StringUtils.removeStart(token, "Bearer").trim();
        return userDao.findByToken(token);
    }

}
