package com.edu.assistant.exception;

import javax.naming.AuthenticationException;


public class ForbiddenDeleteException extends AuthenticationException {


    public ForbiddenDeleteException(String msg) {
        super(msg);
    }

}
