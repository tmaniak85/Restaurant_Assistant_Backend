package com.edu.assistant.controller;

import com.edu.assistant.exception.*;
import com.edu.assistant.dto.ValidationError;
import com.edu.assistant.dto.ValidationErrors;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Data
@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler({UsernameExistException.class, TableInUserExistException.class, OrderInTableExistException.class,
            BadCredentialsException.class, ForbiddenDeleteException.class, BadDateFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrors showRegisterBadRequestException(Exception exception) {
        String[] codeAndMessage = exception.getMessage().split("-");
        ValidationError error = new ValidationError("user or table or order or date", codeAndMessage[1],
                codeAndMessage[0]);
        return new ValidationErrors(Arrays.asList(error));
    }

    @ExceptionHandler({UserInTableExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrors showRegisterNullPointerException(UserInTableExistException exception) {
        String[] codeAndMessageAndHelperMessage = exception.getMessage().split("-");
        ValidationError error = new ValidationError("order", codeAndMessageAndHelperMessage[2],
                codeAndMessageAndHelperMessage[1], codeAndMessageAndHelperMessage[0]);
        return new ValidationErrors(Arrays.asList(error));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrors showValidation(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ValidationError> errorList = (List)fieldErrors.stream().map((f) -> {
            String[] codeAndMessage = f.getDefaultMessage().split("-");
            return new ValidationError(f.getField(), codeAndMessage[1], codeAndMessage[0]);
        }).collect(Collectors.toList());
        return new ValidationErrors(errorList);
    }

}
