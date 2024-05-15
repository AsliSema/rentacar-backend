package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllRentalResponse;

import java.util.List;

public interface RentalService {

    CreatedRentalResponse add(CreateRentalRequest request);

    List<GetAllRentalResponse> getAll();
}
