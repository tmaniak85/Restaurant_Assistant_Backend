package com.edu.assistant.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class UserCredentialsPasswordDto {


    @NotEmpty(
            message = "C005-Empty password field"
    )
    @Size(
            min = 8,
            max = 30,
            message = "C002-Password should have size between 8 and 30"
    )
    private String password;

}
