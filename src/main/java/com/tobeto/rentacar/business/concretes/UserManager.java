package com.tobeto.rentacar.business.concretes;


import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.dtos.requests.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.UserBusinessRules;
import com.tobeto.rentacar.core.enums.LicenseClass;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.dataAccess.abstracts.LicenseRepository;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.Brand;
import com.tobeto.rentacar.entities.concretes.License;
import com.tobeto.rentacar.entities.concretes.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserManager implements UserService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;
    private UserBusinessRules userBusinessRules;
    private LicenseRepository licenseRepository;

    @Override
    public CreatedUserResponse add(CreateUserRequest request) {

        userBusinessRules.checkIfUserExist(request.getEmail());
        userBusinessRules.checkIfPasswordWrittenCorrectly(request.getPassword(), request.getConfirmPassword());

        User user = this.modelMapperService.forRequest().map(request, User.class);
        user.setCreatedDate(LocalDateTime.now());
        User createdUser = this.userRepository.save(user);

//        User user = new User();
//        user.setIdentityNumber(request.getIdentityNumber());
//        user.setCity(request.getCity());
//        user.setCompanyName(request.getCompanyName());
//        user.setPassword(request.getPassword());
//        user.setEmail(request.getEmail());
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setCreatedDate(LocalDateTime.now());
//
//        User createdUser = this.userRepository.save(user);
//
//        License license = new License();
//
//        license.setLicenseNumber(request.getLicenseLicenseNumber());
//        license.setLicenseClass(request.getLicenseLicenseClass());
//        license.setIssueDate(request.getLicenseIssueDate());
//
//        License createdLicense = this.licenseRepository.save(license);

        CreatedUserResponse createdUserResponse = this.modelMapperService.forResponse().map(createdUser, CreatedUserResponse.class);
        return createdUserResponse;

    }

    @Override
    public GetUserResponse get(int id) {
        User user = userRepository.findById(id).orElseThrow();
        GetUserResponse response = modelMapperService.forResponse().map(user, GetUserResponse.class);

        return response;
    }

    @Override
    public UpdatedUserResponse update(UpdateUserRequest request, int id) {

        User user = userRepository.findById(id).orElseThrow();
        User updatedUser = modelMapperService.forRequest().map(request, User.class);

        user.setId(id);
        user.setUpdatedDate(LocalDateTime.now());
        user.setCity(updatedUser.getCity() != null ? updatedUser.getCity() : user.getCity());
        user.setCompanyName(updatedUser.getCompanyName() != null ? updatedUser.getCompanyName() : user.getCompanyName());
        user.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : user.getEmail());
        user.setFirstName(updatedUser.getFirstName() != null ? updatedUser.getFirstName() : user.getFirstName());
        user.setLastName(updatedUser.getLastName() != null ? updatedUser.getLastName() : user.getLastName());
        user.setPhoneNumber(updatedUser.getPhoneNumber() != null ? updatedUser.getPhoneNumber() : user.getPhoneNumber());
        user.setPassword(updatedUser.getPassword() != null ? updatedUser.getPassword() : user.getPassword());
        user.setConfirmPassword(updatedUser.getConfirmPassword() != null ? updatedUser.getConfirmPassword() : user.getConfirmPassword());
        user.setIdentityNumber(updatedUser.getIdentityNumber() != null ? updatedUser.getIdentityNumber() : user.getIdentityNumber());

        userRepository.save(user);
        UpdatedUserResponse response = modelMapperService.forResponse().map(user, UpdatedUserResponse.class);

        return response;
    }

    @Override
    public List<GetAllUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> response = users.stream().map(user -> modelMapperService.forResponse().map(user, GetAllUserResponse.class)).collect(Collectors.toList());
        return response;
    }


    @Override
    public Result deleteById(int id) {
        userRepository.deleteById(id);
        return new Result(true, "User Deleted!");
    }


}
