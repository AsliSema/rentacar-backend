package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.requests.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.CarBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.entities.concretes.Brand;
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
    public GetCarResponse get(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse response = modelMapperService.forResponse().map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public UpdateCarResponse update(UpdateCarRequest request, int id) {
        Car car = carRepository.findById(id).orElseThrow();
        Car updatedCar = modelMapperService.forRequest().map(request, Car.class);


        car.setId(id);
        car.setUpdatedDate(LocalDateTime.now());
        car.setPlate(updatedCar.getPlate() != null ? updatedCar.getPlate() : car.getPlate());
        car.setModelYear(updatedCar.getModelYear() != 0 ? updatedCar.getModelYear() : car.getModelYear());
        car.setState(updatedCar.getState() != 0 ? updatedCar.getState() : car.getState());
        car.setDailyPrice(updatedCar.getDailyPrice() != 0 ? updatedCar.getDailyPrice() : car.getDailyPrice());

        carRepository.save(car);
        UpdateCarResponse response = modelMapperService.forResponse().map(car, UpdateCarResponse.class);

        return response;

    }

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> response = cars.stream().map(car -> modelMapperService.forResponse().map(car, GetAllCarResponse.class)).collect(Collectors.toList());
        return response;
    }
}
