package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.core.enums.CarState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedCarResponse {

    private int id;
    private int modelYear;
    private String plate;
    private int kilometer;
    private CarState state;
    private double dailyPrice;
    private String location;
    private String color;
    private int modelId;
    private int userId;
}
