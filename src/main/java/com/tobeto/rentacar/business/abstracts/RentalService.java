package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedRentalResponse;

public interface RentalService {

    CreatedRentalResponse add(CreateRentalRequest request);
}
