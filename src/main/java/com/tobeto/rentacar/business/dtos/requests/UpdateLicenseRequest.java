package com.tobeto.rentacar.business.dtos.requests;

import com.tobeto.rentacar.core.enums.LicenseClass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLicenseRequest {

    @Size(min = 6, max = 6, message = "Driver's license number must be 6 characters long")
    private String licenseNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    private LicenseClass licenseClass;

}
