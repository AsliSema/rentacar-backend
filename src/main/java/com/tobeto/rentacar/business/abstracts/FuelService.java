package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.core.utilities.results.Result;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest request);

    UpdateFuelResponse update(UpdateFuelRequest request, int id);

    GetFuelResponse getById(int id);
    List<GetAllFuelResponse> getAll();

    Result deleteById(int id);
}
