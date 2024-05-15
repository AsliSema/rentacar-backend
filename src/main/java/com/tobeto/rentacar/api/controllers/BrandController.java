package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.dtos.requests.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateBrandResponse;
import com.tobeto.rentacar.core.utilities.results.Result;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")  //Frontend de gelen istekleri hatasız karşılaması için
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandController {
    private BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBrandResponse add(@Valid @RequestBody CreateBrandRequest createBrandRequest){
        return brandService.add(createBrandRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetBrandResponse getBrand(@PathVariable int id){
        return brandService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllBrandResponse> getAll(){
        return brandService.getAll();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateBrandResponse update(@Valid @RequestBody UpdateBrandRequest request, @PathVariable int id){
        return brandService.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id){
        return brandService.deleteById(id);
    }
}
