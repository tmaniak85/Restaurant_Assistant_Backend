package com.edu.assistant.exception;

import javax.naming.AuthenticationException;


public class UsernameExistException extends AuthenticationException {


    public UsernameExistException(String msg) {
        super(msg);
    }

}
