package com.tobeto.rentacar.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalByUserIdResponse {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalPrice;
    private int carId;
    private String carColor;
    private String carPlate;
    private String carColorName;
    private String carImagePath;
    private double carDailyPrice;
    private int modelId;
    private String modelName;
    private int transmissionId;
    private String transmissionName;
    private int fuelId;
    private String fuelName;
    private String brandName;

}
