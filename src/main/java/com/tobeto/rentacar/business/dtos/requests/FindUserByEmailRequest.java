package com.tobeto.rentacar.business.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FindUserByEmailRequest {
    private String email;
}
