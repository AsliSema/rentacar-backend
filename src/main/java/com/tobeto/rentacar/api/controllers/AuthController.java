package com.tobeto.rentacar.api.controllers;


import com.tobeto.rentacar.business.abstracts.AuthService;
import com.tobeto.rentacar.business.dtos.requests.LoginRequest;
import com.tobeto.rentacar.business.dtos.requests.RegisterRequest;
import com.tobeto.rentacar.business.dtos.responses.LoginResponse;
import com.tobeto.rentacar.business.dtos.responses.RegisterResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse registerUser(@Valid @RequestBody RegisterRequest request){
       return authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }

}
