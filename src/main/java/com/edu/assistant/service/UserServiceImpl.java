package com.edu.assistant.service;

import com.edu.assistant.dao.UserDao;
import com.edu.assistant.model.User;
import com.edu.assistant.model.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> list() {
        return userDao.findAll();
    }

    public List<User> showAvailableUsers() {
        return userDao.findAllByAvailableIs(true);
    }


    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public UserDetails findByToken(String token) {
        return userDao.findByToken(token);
    }

    public UserDetails findById(Long id) {
        return userDao.getById(id);
    }

    public UserDetails register(User introducedUser) {
        User user = new User();
        user.setUsername(introducedUser.getUsername());
        user.setPassword(passwordEncoder.encode(introducedUser.getPassword()));
        user.setFirstName(introducedUser.getFirstName());
        user.setLastName(introducedUser.getLastName());
        user.setUserAuthority(introducedUser.getUserAuthority());
        return userDao.save(user);
    }
    public void registerAdminTestUser() {
        User user = new User();
        user.setUsername("ADMIN");
        user.setPassword("ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstName("ADMIN");
        user.setLastName("ADMIN");
        user.setUserAuthority(UserAuthority.ADMIN);
        userDao.save(user);
    }

    public void partialUpdated(User introducedUser) {
        User user = introducedUser;
        user.setPassword(passwordEncoder.encode(introducedUser.getPassword()));
        userDao.save(user);
    }

    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        if(!passwordEncoder.matches(password, user.getPassword())) {
           throw new UsernameNotFoundException(username);
        }

        user.setToken(UUID.randomUUID().toString());
        user.setAvailable(true);
        return userDao.save(user);
    }

    public void logout(String username) {
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        user.setAvailable(false);
        user.setToken(null);
        userDao.save(user);
    }

    public void delete(Long id) {
        userDao.deleteById(id);
    }
}
