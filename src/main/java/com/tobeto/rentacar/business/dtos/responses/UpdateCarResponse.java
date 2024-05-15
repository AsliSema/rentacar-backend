package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.core.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private int kilometer;
    private String location;
    private CarState state;
    private double dailyPrice;
    private String color;
    private int userId;
}
