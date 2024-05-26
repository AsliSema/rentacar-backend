package com.tobeto.rentacar.business.dtos.responses;
import com.tobeto.rentacar.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String bareerToken;

}
