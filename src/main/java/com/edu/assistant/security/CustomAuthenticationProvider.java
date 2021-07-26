package com.edu.assistant.security;

import com.edu.assistant.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


    @Autowired
    private UserServiceImpl userService;


    @Override
    protected void additionalAuthenticationChecks
            (UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        String token = (String)authentication.getCredentials();
        return userService.findUserByToken(token);
    }

}
