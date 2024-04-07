package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    BrandRepository brandRepository;
    public void brandNameCanNotBeDuplicated(String brandName){
        Optional<Brand> brand = brandRepository.findByNameIgnoreCase(brandName);
        if(brand.isPresent()){
            throw new BusinessException("Brand Exists!");
        }
    }
}
