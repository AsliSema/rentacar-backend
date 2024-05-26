package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.FindUserByEmailRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.core.utilities.results.Result;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {

    GetUserResponse get(int id);

    UpdatedUserResponse update(UpdateUserRequest updateUserRequest, int id);

    List<GetAllUserResponse> getAll();

    FindByEmailResponse getUserByEmail(String email);

    Result deleteById(int id);

}
