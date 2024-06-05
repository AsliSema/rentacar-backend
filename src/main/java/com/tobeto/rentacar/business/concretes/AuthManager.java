package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.AuthService;
import com.tobeto.rentacar.business.dtos.requests.LoginRequest;
import com.tobeto.rentacar.business.dtos.requests.RegisterRequest;
import com.tobeto.rentacar.business.dtos.responses.LoginResponse;
import com.tobeto.rentacar.business.dtos.responses.RegisterResponse;
import com.tobeto.rentacar.business.rules.UserBusinessRules;
import com.tobeto.rentacar.core.enums.Role;
import com.tobeto.rentacar.core.security.JwtService;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;
    private UserBusinessRules userBusinessRules;
    private BCryptPasswordEncoder passwordEncoder;

    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        userBusinessRules.checkIfUserExist(request.getEmail());
        userBusinessRules.checkIfPasswordWrittenCorrectly(request.getPassword(), request.getConfirmPassword());

        User newUser = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .identityNumber(request.getIdentityNumber())
                .city(request.getCity().toLowerCase())
                .companyName(request.getCompanyName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        newUser.setCreatedDate(LocalDateTime.now());
        userRepository.save(newUser);

        RegisterResponse response = this.modelMapperService.forResponse().map(newUser, RegisterResponse.class);
        return response;

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        userBusinessRules.checkUserPresence(request.getEmail());
        Optional<User> user = userRepository.findByEmail(request.getEmail());


        if(!passwordEncoder.matches(request.getPassword(), user.get().getPassword())){
            throw new IllegalStateException("Wrong email or password!");
        }

        try{

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String token= null;

            if (authentication.isAuthenticated()) {
                token = jwtService.generateToken(request.getEmail());
            }else{
                new BusinessException("Invalid email!");
            }

            LoginResponse response = LoginResponse
                    .builder()
                    .id(user.get().getId())
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .email(user.get().getEmail())
                    .role(user.get().getRole().name())
                    .bareerToken(token)
                    .build();

            return response;
        }catch (Exception e){
            throw new BusinessException("Authentication failed: " + e.getMessage());
        }


    }
}
