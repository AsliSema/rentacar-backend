package com.tobeto.rentacar.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedRentalResponse {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int totalPrice;

    private int carId;

    private int userId;

}
