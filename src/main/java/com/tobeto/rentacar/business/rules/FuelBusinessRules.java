package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.FuelRepository;
import com.tobeto.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FuelBusinessRules {
    private FuelRepository fuelRepository;

    public void fuelNameCanNotBeDuplicated(String fuelName){
        Optional<Fuel> fuel = fuelRepository.findByNameIgnoreCase(fuelName);
        if(fuel.isPresent()){
            throw new BusinessException("Fuel Exists!");
        }
    }
}
