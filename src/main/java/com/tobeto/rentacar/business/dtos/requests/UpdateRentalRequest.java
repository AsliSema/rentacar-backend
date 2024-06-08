package com.tobeto.rentacar.business.dtos.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentalRequest {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "The start date cannot be further back than today.")
    private LocalDate startDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "The end date cannot be further back than today.")
    private LocalDate endDate;


    private int carId;


    private int userId;
}
