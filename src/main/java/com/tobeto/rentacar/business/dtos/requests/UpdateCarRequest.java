package com.tobeto.rentacar.business.dtos.requests;

import com.tobeto.rentacar.core.enums.CarState;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarRequest {
    private int modelYear;

    private String plate;

    private int kilometer;


    private CarState state;

    private double dailyPrice;

    private String color;

    private String location;

    private int modelId;

    private int userId;

}
