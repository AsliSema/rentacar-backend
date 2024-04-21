package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllModelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetModelResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    CreatedModelResponse add(CreateModelRequest createModelRequest);

    GetModelResponse getById(int id);
    UpdateModelResponse update(UpdateModelRequest request, int id);

    List<GetAllModelResponse> getAll();
}
