package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.LicenseService;
import com.tobeto.rentacar.business.dtos.requests.CreateLicenseRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateLicenseRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.LicenseBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.dataAccess.abstracts.LicenseRepository;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LicenseManager implements LicenseService {

    private ModelMapperService modelMapperService;
    private LicenseRepository licenseRepository;
    private UserRepository userRepository;
    private LicenseBusinessRules licenseBusinessRules;

    @Override
    public CreatedLicenseResponse add(CreateLicenseRequest request, int userId) {

        licenseBusinessRules.checkIfUserExists(userId);

        User user = userRepository.findById(userId);

        License license = this.modelMapperService.forRequest().map(request, License.class);

        license.setUser(user);
        license.setCreatedDate(LocalDateTime.now());
        License createdLicense = this.licenseRepository.save(license);
        CreatedLicenseResponse response = this.modelMapperService.forResponse().map(createdLicense, CreatedLicenseResponse.class);


        user.setLicense(license);
        userRepository.save(user);

        return response;
    }

    @Override
    public GetLicenseResponse getById(int id) {

        licenseBusinessRules.checkIfLicenseExists(id);

        License license = licenseRepository.findById(id);
        GetLicenseResponse response = modelMapperService.forResponse().map(license, GetLicenseResponse.class);

        return response;
    }

    @Override
    public UpdatedLicenseResponse update(UpdateLicenseRequest request, int licenseId, int userId) {

        licenseBusinessRules.checkIfLicenseExists(licenseId);
        licenseBusinessRules.checkIfUserExists(userId);
        licenseBusinessRules.checkIfUserHasLicense(userId, licenseId);

        User user = userRepository.findById(userId);
        License license = licenseRepository.findById(licenseId);
        License updatedLicense = modelMapperService.forRequest().map(request, License.class);

        license.setId(licenseId);
        license.setUser(user);
        license.setUpdatedDate(LocalDateTime.now());
        license.setLicenseClass(updatedLicense.getLicenseClass() != null ? updatedLicense.getLicenseClass() : license.getLicenseClass());
        license.setLicenseNumber(updatedLicense.getLicenseNumber() != null ? updatedLicense.getLicenseNumber() : license.getLicenseNumber());
        license.setIssueDate(updatedLicense.getIssueDate() != null ? updatedLicense.getIssueDate() : license.getIssueDate());

        licenseRepository.save(license);

        UpdatedLicenseResponse response = modelMapperService.forResponse().map(license, UpdatedLicenseResponse.class);

        return response;
    }


    @Override
    public List<GetAllLicenseResponse> getAll() {
        List<License> licenses = licenseRepository.findAll();
        List<GetAllLicenseResponse> response = licenses.stream().map(license -> modelMapperService.forResponse().map(license, GetAllLicenseResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public Result deleteById(int id) {

        licenseBusinessRules.checkIfLicenseExists(id);
        User user = userRepository.findByLicenseId(id);

        user.setLicense(null);
        licenseRepository.deleteById(id);

        return new Result(true, "License Deleted!");
    }


}
