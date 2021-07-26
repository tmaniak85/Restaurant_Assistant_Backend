package com.edu.assistant.dto;

import lombok.Data;
import java.util.List;


@Data
public class ValidationErrors {


    private List<ValidationError> validationErrorList;


    public ValidationErrors(List<ValidationError> validationErrorList) {
        this.validationErrorList = validationErrorList;
    }

}
