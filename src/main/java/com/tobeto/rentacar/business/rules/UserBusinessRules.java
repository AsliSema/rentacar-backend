package com.tobeto.rentacar.business.rules;


import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserBusinessRules {
    private UserRepository userRepository;

    public void checkIfUserExist(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new BusinessException("User Exists!");
        }
    }

    public void checkIfPasswordWrittenCorrectly(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            throw new BusinessException("Passwords doesn't match!");
        }
    }

    public void checkUserPresence(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            throw new BusinessException("You Need to Register First!");
        }
    }


}
