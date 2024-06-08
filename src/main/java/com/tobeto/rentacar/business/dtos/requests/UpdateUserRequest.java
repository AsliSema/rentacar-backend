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

    @Size(min = 2, max = 20, message = "First name must be at least 2 characters!")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name must be at least 2 characters!")
    private String lastName;

    @Email(message = "Invalid email address!")
    private String email;

    @Size(min = 5, max = 25, message = "Password must be between 5 and 25 characters!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one lowercase letter, one uppercase letter and one digit character.")
    private String password;

    private String confirmPassword;

    @Size(min = 10, max = 10)
    private String phoneNumber;

    @Size(min = 11, max = 11)
    private String identityNumber;

    private String city;

}
