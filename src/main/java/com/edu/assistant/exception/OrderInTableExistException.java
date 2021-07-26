package com.edu.assistant.exception;

import javax.naming.AuthenticationException;


public class OrderInTableExistException extends AuthenticationException {


    public OrderInTableExistException(String msg) {
        super(msg);
    }

}
