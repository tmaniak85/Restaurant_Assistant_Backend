package com.edu.assistant.exception;


public class NotFoundException extends RuntimeException {


    String msg;


    public NotFoundException(String msg) {
        this.msg = msg;
    }

}
