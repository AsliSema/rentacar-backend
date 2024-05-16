package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.abstracts.LicenseService;
import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.dtos.responses.GetLicenseResponse;
import com.tobeto.rentacar.business.dtos.responses.GetUserResponse;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.LicenseRepository;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.License;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LicenseBusinessRules {

    private UserRepository userRepository;
    private LicenseRepository licenseRepository;

    public void checkIfUserExists(int userId){
        User user = userRepository.findById(userId);

        if(user == null){
            throw new BusinessException("User is not exists!");
        }
    }

    public void checkIfUserHasLicense(int userId, int licenseId){
        User user = userRepository.findById(userId);

        if(user.getLicense() == null || !(user.getLicense().getId() == licenseId)){
            throw new BusinessException("This user does not have this license!");
        }
    }

    public void checkIfLicenseExists(int licenseId){
        License license = licenseRepository.findById(licenseId);

        if(license == null){
            throw new BusinessException("License is not exists!");
        }

    }

}
