package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.dtos.requests.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
public class ModelController {
    private ModelService modelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse add(@Valid @RequestBody CreateModelRequest createModelRequest){
        return modelService.add(createModelRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetModelResponse get(@PathVariable int id){
        return modelService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllModelResponse> getAll(){
        return modelService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateModelResponse update(@Valid @RequestBody UpdateModelRequest request, @PathVariable int id){
        return modelService.update(request, id);
    }
}
