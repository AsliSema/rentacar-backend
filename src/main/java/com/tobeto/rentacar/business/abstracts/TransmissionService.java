package com.tobeto.rentacar.business.abstracts;


import com.tobeto.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateTransmissionResponse;

import java.util.List;

public interface TransmissionService {

    CreatedTransmissionResponse add(CreateTransmissionRequest request);

    UpdateTransmissionResponse update(UpdateTransmissionRequest request, int id);
    List<GetAllTransmissionResponse> getAll();
}
