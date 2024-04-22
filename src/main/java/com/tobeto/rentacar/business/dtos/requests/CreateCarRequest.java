package com.tobeto.rentacar.business.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {
    @NotNull
    @Min(value = 2000)
    private int modelYear;

    @NotNull
    @Size(min=5, max=11)
    private String plate;

    @NotNull
    @Min(1)
    @Max(3)
    private int state;

    @NotNull
    private double dailyPrice;

    @NotNull
    @Positive
    private int modelId;

    @NotNull
    @Positive
    private int userId;

}
