package com.edu.assistant.service;

import com.edu.assistant.model.Tables;
import com.edu.assistant.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface UserService extends UserDetailsService {

    public UserDetails findByToken(String token);
    public User loadUserByUsername(String username);

}
