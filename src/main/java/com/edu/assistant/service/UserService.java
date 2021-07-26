package com.edu.assistant.service;

import com.edu.assistant.dto.UserCredentialsDto;
import com.edu.assistant.dto.UserCredentialsPasswordDto;
import com.edu.assistant.exception.BadCredentialsException;
import com.edu.assistant.exception.ForbiddenDeleteException;
import com.edu.assistant.exception.TableInUserExistException;
import com.edu.assistant.exception.UsernameExistException;
import com.edu.assistant.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService extends UserDetailsService {

    UserDetails register(UserCredentialsDto introducedUser) throws UsernameExistException;
    void registerAdminTestUser();
    void changePassword(UserCredentialsPasswordDto userCredentialsPasswordDto, long id);
    UserDetails findById(Long id);
    List<User> showActiveUsers();
    List<User> showActiveWaiters();
    List<User> showAvailableWaiters();
    User login(String username, String password) throws BadCredentialsException;
    void logout(String username);
    void setAsNonActive(Long id, String authorization) throws TableInUserExistException, ForbiddenDeleteException;
    void deleteAdminUser(Long id, String authorization) throws ForbiddenDeleteException;
    User loadUserByUsername(String username);
    UserDetails findUserByToken(String token);

}
