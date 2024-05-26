package com.tobeto.rentacar.business.dtos.responses;

import com.tobeto.rentacar.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
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
