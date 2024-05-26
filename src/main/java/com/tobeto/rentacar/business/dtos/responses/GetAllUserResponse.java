package com.tobeto.rentacar.business.dtos.responses;


import com.tobeto.rentacar.core.enums.LicenseClass;
import com.tobeto.rentacar.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllUserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String phoneNumber;
    private String identityNumber;
    private String city;
    private int licenseId;
    private String licenseLicenseNumber;
    private String licenseIssueDate;
    private String licenseLicenseClass;
    private String role;
    private LocalDateTime createdDate;
}
