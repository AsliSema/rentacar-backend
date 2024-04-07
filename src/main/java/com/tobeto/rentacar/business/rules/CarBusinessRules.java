package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.concretes.Car;
import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CarBusinessRules {
    private CarRepository carRepository;
    private ModelRepository modelRepository;

    public void carPlateCanNotBeDuplicated(String carPlate){
        Optional<Car> car = carRepository.findByPlateIgnoreCase(carPlate);
        if(car.isPresent()){
            throw new BusinessException("Car Plate Exists!");
        }
    }

    public void checkIfModelExists(int modelId){
        Optional<Model> model = modelRepository.findById(modelId);
        if(!model.isPresent()){
            throw new BusinessException("Model couldn't found!");
        }
    }
}
