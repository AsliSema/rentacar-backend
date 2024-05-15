package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.dtos.requests.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.CreatedRentalResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")  //Frontend de gelen istekleri hatasız karşılaması için
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/rentals")
public class RentalController {

    private RentalService rentalService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedRentalResponse add(@Valid @RequestBody CreateRentalRequest request){
        return rentalService.add(request);
    }
}
