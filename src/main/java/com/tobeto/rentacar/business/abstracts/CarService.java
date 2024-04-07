package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedCarResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllCarResponse;

import java.util.List;

public interface CarService {
    CreatedCarResponse add(CreateCarRequest request);

    List<GetAllCarResponse> getAll();
}
