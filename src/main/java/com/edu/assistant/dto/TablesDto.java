package com.edu.assistant.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class TablesDto {


    @Min(value = 1, message = "C001-Number, minimum value: 1")
    @Max(value = 1000, message = "C002-Number, maximum value: 1000")
    private long numberOfTable;
    @Min(value = 1, message = "C004-Number, minimum value: 1")
    @Max(value = 50, message = "C005-Number, maximum value: 50")
    private long numberOfSeats;

}
