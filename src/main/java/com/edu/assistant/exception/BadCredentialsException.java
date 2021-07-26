package com.edu.assistant.exception;

import javax.naming.AuthenticationException;


public class BadCredentialsException extends AuthenticationException {


    public BadCredentialsException(String msg) {
        super(msg);
    }

}
