package com.edu.assistant.dto;

import lombok.Data;


@Data
public class ValidationError {


    private String field;
    private String message;
    private String helperMessage;
    private String code;


    public ValidationError(String field, String message, String code) {
        this.field = field;
        this.message = message;
        this.code = code;
    }

    public ValidationError(String field, String helperMessage, String message, String code) {
        this.field = field;
        this.message = message;
        this.helperMessage = helperMessage;
        this.code = code;
    }

}
