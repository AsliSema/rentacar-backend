package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.core.utilities.results.Result;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    CreatedUserResponse add(CreateUserRequest request);

    GetUserResponse get(int id);

    UpdatedUserResponse update(UpdateUserRequest updateUserRequest, int id);

    List<GetAllUserResponse> getAll();

    Result deleteById(int id);

}
