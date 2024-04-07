package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedUserResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllUserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    CreatedUserResponse add(CreateUserRequest request);

    List<GetAllUserResponse> getAll();

}
