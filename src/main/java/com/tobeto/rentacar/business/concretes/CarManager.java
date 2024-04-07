package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.requests.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedCarResponse;
import com.tobeto.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllFuelResponse;
import com.tobeto.rentacar.business.rules.CarBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.entities.concretes.Car;
import com.tobeto.rentacar.entities.concretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;

    @Override
    public CreatedCarResponse add(CreateCarRequest request) {

        carBusinessRules.carPlateCanNotBeDuplicated(request.getPlate());
        carBusinessRules.checkIfModelExists(request.getModelId());

        Car car = this.modelMapperService.forRequest().map(request, Car.class);
        car.setCreatedDate(LocalDateTime.now());
        Car createdCar = this.carRepository.save(car);
        CreatedCarResponse createdCarResponse = this.modelMapperService.forResponse().map(createdCar, CreatedCarResponse.class);
        return createdCarResponse;
    }

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> response = cars.stream().map(car -> modelMapperService.forResponse().map(car, GetAllCarResponse.class)).collect(Collectors.toList());
        return response;
    }
}
