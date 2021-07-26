package com.edu.assistant.exception;

import javax.naming.AuthenticationException;


public class UserInTableExistException extends AuthenticationException {


    public UserInTableExistException(String msg) {
        super(msg);
    }

}
