package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllBrandResponse;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest createBrandRequest);

    List<GetAllBrandResponse> getAll();
}
