package com.tobeto.rentacar.business.dtos.requests;

import com.tobeto.rentacar.core.enums.CarState;
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
    @Size(min=7, max=8)
    private String plate;

    @NotNull
    private int kilometer;

    private CarState state;

    @NotNull
    private double dailyPrice;

    @NotNull
    private String color;

    @NotNull
    private String location;

    @NotNull
    @Positive
    private int modelId;

    @NotNull
    @Positive
    private int userId;

}
