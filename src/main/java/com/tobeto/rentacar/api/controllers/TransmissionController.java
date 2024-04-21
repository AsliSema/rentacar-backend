package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.dtos.requests.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transmission")
public class TransmissionController {
    private TransmissionService transmissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse add(@Valid @RequestBody CreateTransmissionRequest createTransmissionRequest){
        return transmissionService.add(createTransmissionRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateTransmissionResponse update(@Valid @RequestBody UpdateTransmissionRequest request, @PathVariable int id){
        return transmissionService.update(request, id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllTransmissionResponse> getAll(){
        return transmissionService.getAll();
    }
}
