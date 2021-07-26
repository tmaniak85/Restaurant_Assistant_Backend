package com.edu.assistant.exception;

import javax.naming.AuthenticationException;


public class TableInUserExistException extends AuthenticationException {


    public TableInUserExistException(String msg) {
        super(msg);
    }

}
