package com.tobeto.rentacar.business.dtos.responses;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedRentalResponse {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int totalPrice;

    private int carId;

    private int userId;
}
