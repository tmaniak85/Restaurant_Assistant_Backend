package com.edu.assistant.dto;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class MenuDto {


    @NotNull(
            message = "C001-Empty username field"
    )
    @Size(
            min = 3,
            max = 16,
            message = "C002-Dish name should have size between 3 and 16"
    )
    private String name;
    private boolean status;
    @NotNull(
            message = "C003-Empty username field"
    )
    @Min(value = 0, message = "C004-Number, minimum value: 1")
    @Max(value = 1000, message = "C005-Number, maximum value: 1000")
    private Double price;

}
