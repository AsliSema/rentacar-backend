package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.FuelService;
import com.tobeto.rentacar.business.dtos.requests.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fuels")
public class FuelController {
    private FuelService fuelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse add(@Valid @RequestBody CreateFuelRequest createFuelRequest){
        return fuelService.add(createFuelRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateFuelResponse update(@Valid @RequestBody UpdateFuelRequest request, @PathVariable int id){
        return fuelService.update(request, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllFuelResponse> getAll(){
        return fuelService.getAll();
    }
}
