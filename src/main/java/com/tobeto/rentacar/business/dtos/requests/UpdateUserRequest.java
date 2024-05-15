package com.tobeto.rentacar.business.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String confirmPassword;

    private String companyName;

    private String phoneNumber;

    private String identityNumber;

    private String city;

}
