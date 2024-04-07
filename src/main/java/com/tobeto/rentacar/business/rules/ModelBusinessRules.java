package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.dataAccess.abstracts.FuelRepository;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.tobeto.rentacar.entities.concretes.Brand;
import com.tobeto.rentacar.entities.concretes.Fuel;
import com.tobeto.rentacar.entities.concretes.Model;
import com.tobeto.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private FuelRepository fuelRepository;
    private TransmissionRepository transmissionRepository;


    public void modelNameCanNotBeDuplicated(String modelName){
        Optional<Model> model = modelRepository.findByNameIgnoreCase(modelName);

        if(model.isPresent()){
            throw new BusinessException("Model Exists!");
        }
    }

    public void checkIfBrandExists(int brandId){
        Optional<Brand> brand = brandRepository.findById(brandId);

        if(!brand.isPresent()){
            throw new BusinessException("Brand couldn't found!");
        }

    }

    public void checkIfFuelExists(int fuelId){
        Optional<Fuel> fuel = fuelRepository.findById(fuelId);

        if(!fuel.isPresent()){
            throw new BusinessException("Fuel couldn't found!");
        }
    }

    public void checkIfTransmissionExists(int transmissionId){
        Optional<Transmission> transmission = transmissionRepository.findById(transmissionId);

        if(!transmission.isPresent()){
            throw new BusinessException("Transmission couldn't found!");
        }
    }


}
