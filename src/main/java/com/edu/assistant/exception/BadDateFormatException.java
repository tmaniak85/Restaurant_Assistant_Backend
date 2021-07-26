package com.edu.assistant.exception;

import javax.naming.AuthenticationException;


public class BadDateFormatException extends AuthenticationException {


    public BadDateFormatException(String msg) {
        super(msg);
    }

}
