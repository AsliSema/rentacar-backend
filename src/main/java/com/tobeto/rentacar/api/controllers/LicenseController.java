package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.LicenseService;
import com.tobeto.rentacar.business.dtos.requests.CreateLicenseRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateLicenseRequest;
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
@RequestMapping("api/v1/licenses")
public class LicenseController {

    private LicenseService licenseService;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedLicenseResponse add(@Valid @RequestBody CreateLicenseRequest request, @PathVariable int userId){
        return licenseService.add(request, userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllLicenseResponse> getAll(){
        return licenseService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetLicenseResponse get(@PathVariable int id){
        return licenseService.getById(id);
    }


    @PutMapping("/{licenseId}/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedLicenseResponse update(@Valid @RequestBody UpdateLicenseRequest request, @PathVariable int licenseId, @PathVariable int userId){
        return licenseService.update(request, licenseId, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id){
        return licenseService.deleteById(id);
    }
}
