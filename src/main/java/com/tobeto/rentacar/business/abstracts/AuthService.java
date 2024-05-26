package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.LoginRequest;
import com.tobeto.rentacar.business.dtos.requests.RegisterRequest;
import com.tobeto.rentacar.business.dtos.responses.LoginResponse;
import com.tobeto.rentacar.business.dtos.responses.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
