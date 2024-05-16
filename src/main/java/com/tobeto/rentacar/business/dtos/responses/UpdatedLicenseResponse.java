package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.core.enums.LicenseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedLicenseResponse {

    private int id;

    private String licenseNumber;

    private LocalDate issueDate;

    private LicenseClass licenseClass;

}
