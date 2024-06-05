package com.tobeto.rentacar.business.concretes;


import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.dtos.requests.CreateUserRequest;
import com.tobeto.rentacar.business.dtos.requests.FindUserByEmailRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateUserRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.UserBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.dataAccess.abstracts.LicenseRepository;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.License;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserManager implements UserService {

    private UserRepository userRepository;
    private LicenseRepository licenseRepository;
    private ModelMapperService modelMapperService;

    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public GetUserResponse get(int id) {
        User user = userRepository.findById(id);

        GetUserResponse response = modelMapperService.forResponse().map(user, GetUserResponse.class);
        return response;
    }

    @Override
    public UpdatedUserResponse update(UpdateUserRequest request, int id) {

        User user = userRepository.findById(id);
        User updatedUser = modelMapperService.forRequest().map(request, User.class);

        user.setId(id);
        user.setUpdatedDate(LocalDateTime.now());
        user.setCity(updatedUser.getCity() != null ? updatedUser.getCity() : user.getCity());
        user.setCompanyName(updatedUser.getCompanyName() != null ? updatedUser.getCompanyName() : user.getCompanyName());
        user.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : user.getEmail());
        user.setFirstName(updatedUser.getFirstName() != null ? updatedUser.getFirstName() : user.getFirstName());
        user.setLastName(updatedUser.getLastName() != null ? updatedUser.getLastName() : user.getLastName());
        user.setPhoneNumber(updatedUser.getPhoneNumber() != null ? updatedUser.getPhoneNumber() : user.getPhoneNumber());
        user.setPassword(updatedUser.getPassword() != null ? passwordEncoder.encode(updatedUser.getPassword()) : user.getPassword());
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
    public FindByEmailResponse getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        FindByEmailResponse response = modelMapperService.forResponse().map(user.get(), FindByEmailResponse.class);
        return response;
    }


    @Override
    public Result deleteById(int id) {
        userRepository.deleteById(id);
        return new Result(true, "User Deleted!");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not exists!"));
    }
}
