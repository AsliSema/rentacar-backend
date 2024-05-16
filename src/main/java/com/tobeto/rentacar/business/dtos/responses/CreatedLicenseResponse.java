package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.core.enums.LicenseClass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedLicenseResponse {

    private int id;

    private String licenseNumber;

    private LocalDate issueDate;

    private LicenseClass licenseClass;

    private int userId;
}
