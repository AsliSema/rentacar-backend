package com.tobeto.rentacar.business.dtos.responses;


import com.tobeto.rentacar.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetUserResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String city;
    private String phoneNumber;
    private String identityNumber;
    private int licenseId;
    private String licenseNumber;
    private String issueDate;
    private String licenseClass;
    private Role role;
    private LocalDateTime createdDate;

}
