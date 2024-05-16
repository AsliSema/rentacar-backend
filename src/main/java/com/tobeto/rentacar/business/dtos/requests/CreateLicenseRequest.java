package com.tobeto.rentacar.business.dtos.requests;

import com.tobeto.rentacar.core.enums.LicenseClass;
import com.tobeto.rentacar.entities.concretes.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateLicenseRequest {
    @NotBlank(message = "License number cannot be blank")
    @Size(min = 6, max = 6, message = "Driver's license number must be 6 characters long")
    private String licenseNumber;

    @NotNull(message = "Issue date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @NotNull(message = "License class cannot be null")
    private LicenseClass licenseClass;


}
