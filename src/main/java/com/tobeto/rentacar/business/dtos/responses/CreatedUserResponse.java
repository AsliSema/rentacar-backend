package com.tobeto.rentacar.business.dtos.responses;

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
    private String companyName;
    private LocalDateTime createdDate;

}
