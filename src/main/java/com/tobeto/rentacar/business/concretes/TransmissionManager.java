package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.TransmissionBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.tobeto.rentacar.entities.concretes.Model;
import com.tobeto.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransmissionManager implements TransmissionService {

    private TransmissionRepository transmissionRepository;
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private TransmissionBusinessRules transmissionBusinessRules;

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest request) {

        transmissionBusinessRules.transmissionNameCanNotBeDuplicated(request.getName());

        Transmission transmission = this.modelMapperService.forRequest().map(request, Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission = this.transmissionRepository.save(transmission);
        CreatedTransmissionResponse createdTransmissionResponse = this.modelMapperService.forResponse().map(createdTransmission, CreatedTransmissionResponse.class);
        return createdTransmissionResponse;
    }

    @Override
    public GetTransmissionResponse getById(int id) {
        Transmission transmission = transmissionRepository.findById(id);
        GetTransmissionResponse response = modelMapperService.forResponse().map(transmission, GetTransmissionResponse.class);

        return response;
    }

    @Override
    public UpdateTransmissionResponse update(UpdateTransmissionRequest request, int id) {
        Transmission transmission = transmissionRepository.findById(id);
        Transmission updatedTransmission = modelMapperService.forRequest().map(request, Transmission.class);

        transmission.setId(id);
        transmission.setUpdatedDate(LocalDateTime.now());
        transmission.setName(updatedTransmission.getName() != null ? updatedTransmission.getName() : transmission.getName());

        transmissionRepository.save(transmission);
        UpdateTransmissionResponse response = modelMapperService.forResponse().map(transmission, UpdateTransmissionResponse.class);

        return response;
    }


    @Override
    public List<GetAllTransmissionResponse> getAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();
        List<GetAllTransmissionResponse> response = transmissions.stream().map(transmission -> modelMapperService.forResponse().map(transmission, GetAllTransmissionResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public Result deleteById(int id) {
        transmissionRepository.deleteById(id);
        return new Result(true, "Transmission Deleted!");
    }
}
