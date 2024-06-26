package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedCarResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.GetCarResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateCarResponse;
import com.tobeto.rentacar.core.utilities.results.Result;

import java.util.List;

public interface CarService {
    CreatedCarResponse add(CreateCarRequest request);

    GetCarResponse get(int id);

    UpdateCarResponse update(UpdateCarRequest request, int id);

    List<GetAllCarResponse> getAll();

    Result deleteById(int id);

}
