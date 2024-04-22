package com.tobeto.rentacar.business.dtos.requests;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

    @NotEmpty(message = "First name must not be empty!")
    @Size(min = 2, max = 50, message = "First name must be at least 2 characters!")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty!")
    @Size(min = 2, max = 50, message = "Last name must be at least 2 characters!")
    private String lastName;

    @NotEmpty(message = "Email must not be empty!")
    @Email(message = "Invalid email address!")
    private String email;


    @NotEmpty(message = "Password must not be empty!")
    @Size(min = 5, max = 25, message = "Password must be between 5 and 25 characters!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "Password must contain at least one lowercase letter, one uppercase letter and one digit character.")
    private String password;

    @NotNull
    @Size(min=2, max=30)
    private String companyName;

}
