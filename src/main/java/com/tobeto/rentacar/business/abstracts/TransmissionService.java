package com.tobeto.rentacar.business.abstracts;


import com.tobeto.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.GetTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateTransmissionResponse;
import com.tobeto.rentacar.core.utilities.results.Result;

import java.util.List;

public interface TransmissionService {

    CreatedTransmissionResponse add(CreateTransmissionRequest request);

    GetTransmissionResponse getById(int id);

    UpdateTransmissionResponse update(UpdateTransmissionRequest request, int id);
    List<GetAllTransmissionResponse> getAll();

    Result deleteById(int id);
}
