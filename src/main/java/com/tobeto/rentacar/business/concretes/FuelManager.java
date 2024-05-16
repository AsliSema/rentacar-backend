package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.FuelService;
import com.tobeto.rentacar.business.dtos.requests.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.UpdateFuelResponse;
import com.tobeto.rentacar.business.rules.FuelBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.dataAccess.abstracts.FuelRepository;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.concretes.Fuel;
import com.tobeto.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FuelManager implements FuelService {

    private FuelRepository fuelRepository;
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private FuelBusinessRules fuelBusinessRules;

    @Override
    public CreatedFuelResponse add(CreateFuelRequest request) {

        fuelBusinessRules.fuelNameCanNotBeDuplicated(request.getName());

        Fuel fuel = this.modelMapperService.forRequest().map(request, Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel createdFuel = this.fuelRepository.save(fuel);
        CreatedFuelResponse createdFuelResponse = this.modelMapperService.forResponse().map(createdFuel, CreatedFuelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public UpdateFuelResponse update(UpdateFuelRequest request, int id) {
        Fuel fuel = fuelRepository.findById(id);
        Fuel updatedFuel = modelMapperService.forRequest().map(request, Fuel.class);

        fuel.setId(id);
        fuel.setUpdatedDate(LocalDateTime.now());
        fuel.setName(updatedFuel.getName() != null ? updatedFuel.getName() : fuel.getName());

        fuelRepository.save(fuel);
        UpdateFuelResponse response = modelMapperService.forResponse().map(fuel, UpdateFuelResponse.class);

        return response;
    }

    @Override
    public GetFuelResponse getById(int id) {
        Fuel fuel = fuelRepository.findById(id);
        GetFuelResponse response = modelMapperService.forResponse().map(fuel, GetFuelResponse.class);

        return response;
    }

    @Override
    public List<GetAllFuelResponse> getAll() {
        List<Fuel> fuels = fuelRepository.findAll();
        List<GetAllFuelResponse> response = fuels.stream().map(fuel -> modelMapperService.forResponse().map(fuel, GetAllFuelResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public Result deleteById(int id) {

        List<Model> getAllByFuelId = modelRepository.findByFuelId(id);

        getAllByFuelId.forEach(model -> model.setFuel(null));

        fuelRepository.deleteById(id);
        return new Result(true, "Fuel Deleted!");
    }
}
