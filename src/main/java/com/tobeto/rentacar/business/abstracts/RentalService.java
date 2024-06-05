package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.core.utilities.results.Result;

import java.util.List;

public interface RentalService {

    CreatedRentalResponse add(CreateRentalRequest request);

    List<GetAllRentalResponse> getAll();

    GetRentalResponse getById(int id);

    UpdatedRentalResponse updateById(UpdateRentalRequest request, int id);
    Result deleteById(int id);

    List<GetRentalByUserIdResponse> getRentalByUser(int id);
}
