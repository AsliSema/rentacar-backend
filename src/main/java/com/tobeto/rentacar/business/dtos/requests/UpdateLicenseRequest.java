package com.tobeto.rentacar.business.dtos.requests;

import com.tobeto.rentacar.core.enums.LicenseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLicenseRequest {

    private String licenseNumber;

    private LocalDate issueDate;

    private LicenseClass licenseClass;

}
