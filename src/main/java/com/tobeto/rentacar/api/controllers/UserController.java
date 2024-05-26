package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.dtos.requests.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.FindUserByEmailRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.core.utilities.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")  //Frontend de gelen istekleri hatasız karşılaması için
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetUserResponse get(@PathVariable int id){
        return userService.get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedUserResponse update(@Valid @RequestBody UpdateUserRequest request, @PathVariable int id){
        return userService.update(request, id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllUserResponse> getAll(){
        return userService.getAll();
    }


    @PostMapping("/findByEmail")
    @ResponseStatus(HttpStatus.OK)
    public FindByEmailResponse findByEmail(@RequestBody String email){
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id){
        return userService.deleteById(id);
    }


}
