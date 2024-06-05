package com.tobeto.rentacar.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalResponse {
    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int totalPrice;

    private int carId;

    private String carPlate;

    private double carDailyPrice;

    private int userId;

    private String modelName;

    private String brandName;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String userPhoneNumber;
}
