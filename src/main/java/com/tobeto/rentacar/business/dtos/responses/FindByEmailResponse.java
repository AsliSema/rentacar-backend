package com.tobeto.rentacar.business.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FindByEmailResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String city;
    private String role;
    private String companyName;
    private String identityNumber;
    private String password;
    private String phoneNumber;
    private int licenseId;

}
