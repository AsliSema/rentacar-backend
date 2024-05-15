package com.tobeto.rentacar.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedUserResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String phoneNumber;
    private String identityNumber;
    private String password;
    private String city;
    private String role;
    private int licenseId;
    private LocalDateTime createdDate;

}
