package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateBrandResponse;
import com.tobeto.rentacar.core.utilities.results.Result;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse add(CreateBrandRequest createBrandRequest);

    GetBrandResponse get(int id);

    UpdateBrandResponse update(UpdateBrandRequest updateBrandRequest, int id);

    List<GetAllBrandResponse> getAll();

    Result deleteById(int id);
}
