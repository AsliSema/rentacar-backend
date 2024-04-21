package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.dtos.requests.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.ModelBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;
    private BrandRepository brandRepository;
    private FuelRepository fuelRepository;
    private TransmissionRepository transmissionRepository;

    @Override
    public CreatedModelResponse add(CreateModelRequest createModelRequest) {

        modelBusinessRules.modelNameCanNotBeDuplicated(createModelRequest.getName());
        modelBusinessRules.checkIfBrandExists(createModelRequest.getBrandId());
        modelBusinessRules.checkIfFuelExists(createModelRequest.getFuelId());
        modelBusinessRules.checkIfTransmissionExists(createModelRequest.getTransmissionId());

        Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
        model.setCreatedDate(LocalDateTime.now());
        Model createdModel = this.modelRepository.save(model);
        CreatedModelResponse createdModelResponse = this.modelMapperService.forResponse().map(createdModel, CreatedModelResponse.class);
        return createdModelResponse;
    }

    @Override
    public GetModelResponse getById(int id) {
        Model model = modelRepository.findById(id).orElseThrow();
        GetModelResponse response = modelMapperService.forResponse().map(model, GetModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(UpdateModelRequest request, int id) {
        Model model = modelRepository.findById(id).orElseThrow();
        Model updatedModel = modelMapperService.forRequest().map(request, Model.class);

        model.setId(id);
        model.setUpdatedDate(LocalDateTime.now());
        model.setName(updatedModel.getName() != null ? updatedModel.getName() : model.getName());


        Brand brand = brandRepository.findById(request.getBrandId()).orElseThrow();
        model.setBrand(brand != null ? brand : model.getBrand());

        Fuel fuel = fuelRepository.findById(request.getFuelId()).orElseThrow();
        model.setFuel(fuel != null ? fuel : model.getFuel());

        Transmission transmission = transmissionRepository.findById(request.getTransmissionId()).orElseThrow();
        model.setTransmission(transmission != null ? transmission : model.getTransmission());

        modelRepository.save(model);

        UpdateModelResponse response = modelMapperService.forResponse().map(model, UpdateModelResponse.class);

        return response;

    }

    @Override
    public List<GetAllModelResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelResponse> response = models.stream().map(model -> modelMapperService.forResponse().map(model, GetAllModelResponse.class)).collect(Collectors.toList());
        return response;
    }
}
