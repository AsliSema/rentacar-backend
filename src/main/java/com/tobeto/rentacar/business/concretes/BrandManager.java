package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.dtos.requests.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateBrandResponse;
import com.tobeto.rentacar.business.rules.BrandBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.entities.concretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BrandManager implements BrandService {

    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;
    private BrandBusinessRules brandBusinessRules;


    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {

        //run business rules
        brandBusinessRules.brandNameCanNotBeDuplicated(createBrandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = this.brandRepository.save(brand);
        CreatedBrandResponse createdBrandResponse = this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public GetBrandResponse get(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse response = modelMapperService.forResponse().map(brand, GetBrandResponse.class);

        return response;
    }

    @Override
    public UpdateBrandResponse update(UpdateBrandRequest request, int id) {
        Brand brand = brandRepository.findById(id).orElseThrow();
        Brand updatedBrand = modelMapperService.forRequest().map(request, Brand.class);

        brand.setId(id);
        brand.setUpdatedDate(LocalDateTime.now());
        brand.setName(updatedBrand.getName() != null ? updatedBrand.getName() : brand.getName());

        brandRepository.save(brand);
        UpdateBrandResponse response = modelMapperService.forResponse().map(brand, UpdateBrandResponse.class);

        return response;
    }

    @Override
    public List<GetAllBrandResponse> getAll() {
        List<Brand>  brands = brandRepository.findAll();
        List<GetAllBrandResponse> response = brands.stream().map(brand -> modelMapperService.forResponse().map(brand, GetAllBrandResponse.class)).collect(Collectors.toList());
        return response;
    }
}
