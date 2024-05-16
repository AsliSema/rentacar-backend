package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.requests.CreateLicenseRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateLicenseRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedLicenseResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllLicenseResponse;
import com.tobeto.rentacar.business.dtos.responses.GetLicenseResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdatedLicenseResponse;
import com.tobeto.rentacar.core.utilities.results.Result;

import java.util.List;

public interface LicenseService {
    CreatedLicenseResponse add(CreateLicenseRequest request, int id);

    GetLicenseResponse getById(int id);

    UpdatedLicenseResponse update(UpdateLicenseRequest request, int licenseId, int userId);

    List<GetAllLicenseResponse> getAll();

    Result deleteById(int id);
}
