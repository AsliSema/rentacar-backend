package com.tobeto.rentacar.business.dtos.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "The start date cannot be further back than today.")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "The end date cannot be further back than today.")
    private LocalDate endDate;

    @Positive
    @NotNull
    private int carId;

    @Positive
    @NotNull
    private int userId;
}
