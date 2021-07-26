package com.edu.assistant.dto;

import com.edu.assistant.model.UserAuthority;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class UserCredentialsDto {


    @NotNull(
            message = "C004-Empty username field"
    )
    @Size(
            min = 3,
            max = 16,
            message = "C001-Username should have size between 3 and 16"
    )
    private String username;
    @NotNull(
            message = "C005-Empty password field"
    )
    @Size(
            min = 8,
            max = 30,
            message = "C002-Password should have size between 8 and 30"
    )
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;
    @NotNull(
            message = "C006-Empty userAuthority field"
    )
    private UserAuthority userAuthority;

}
