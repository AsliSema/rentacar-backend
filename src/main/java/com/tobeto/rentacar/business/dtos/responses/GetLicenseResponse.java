package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.core.enums.LicenseClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLicenseResponse {

    private int id;

    private String licenseNumber;

    private LocalDate issueDate;

    private LicenseClass licenseClass;

    private int userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String userPhoneNumber;
}
