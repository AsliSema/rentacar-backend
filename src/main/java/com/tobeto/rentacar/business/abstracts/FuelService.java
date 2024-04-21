package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateFuelResponse;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse add(CreateFuelRequest request);

    UpdateFuelResponse update(UpdateFuelRequest request, int id);
    List<GetAllFuelResponse> getAll();
}
