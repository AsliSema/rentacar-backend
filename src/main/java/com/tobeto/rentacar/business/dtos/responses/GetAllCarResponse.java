package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.core.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private CarState state;
    private double dailyPrice;
    private int kilometer;
    private String location;
    private String color;
    private String imagePath;
    private int modelId;
    private int brandId;
    private int fuelId;
    private int transmissionId;
    private String modelName;
    private String brandName;
    private String fuelName;
    private String transmissionName;
}
