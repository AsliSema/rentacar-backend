package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.dtos.requests.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedUserResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllUserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedUserResponse add(@Valid @RequestBody CreateUserRequest request){
        return userService.add(request);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllUserResponse> getAll(){
        return userService.getAll();
    }
}
