package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.core.enums.LicenseClass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedUserResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String city;
    private String role;
    private int licenseId;
    private LocalDateTime createdDate;

}
