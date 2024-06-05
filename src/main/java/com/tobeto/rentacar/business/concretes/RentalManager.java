package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.dtos.requests.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.requests.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.business.rules.RentalBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.core.utilities.results.Result;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.dataAccess.abstracts.RentalRepository;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private ModelMapperService modelMapperService;
    private RentalRepository rentalRepository;
    private UserRepository userRepository;
    private CarRepository carRepository;
    private RentalBusinessRules rentalBusinessRules;


    @Override
    public CreatedRentalResponse add(CreateRentalRequest request) {

        double totalPrice;

        rentalBusinessRules.checkIfCarExists(request.getCarId());
        rentalBusinessRules.checkIfUserExists(request.getUserId());
        rentalBusinessRules.checkIfUserDoesntHaveLicense(request.getUserId());
        rentalBusinessRules.checkUserLicenseYear(request.getUserId());
        rentalBusinessRules.checkLocation(request.getCarId(), request.getUserId());
        rentalBusinessRules.checkIfCarAvailable(request.getCarId(), request.getStartDate(), request.getEndDate());


        totalPrice = rentalBusinessRules.totalPriceForDateRange(request.getCarId(), request.getStartDate(), request.getEndDate());

        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);

        rental.setTotalPrice(totalPrice);
        rental.setCreatedDate(LocalDateTime.now());
        Rental createdRental = this.rentalRepository.save(rental);

        CreatedRentalResponse createdRentalResponse = this.modelMapperService.forResponse().map(createdRental, CreatedRentalResponse.class);
        return createdRentalResponse;

    }

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalResponse> response = rentals.stream().map(rental -> modelMapperService.forResponse().map(rental, GetAllRentalResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public GetRentalResponse getById(int id) {
        Rental rental = rentalRepository.findById(id);
        GetRentalResponse response =  modelMapperService.forResponse().map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public UpdatedRentalResponse updateById(UpdateRentalRequest request, int id) {

        double totalPrice;

        Rental rental = rentalRepository.findById(id);
        Rental updatedRental = modelMapperService.forRequest().map(request, Rental.class);

        rental.setId(id);
        rental.setUpdatedDate(LocalDateTime.now());
        rental.setStartDate(updatedRental.getStartDate() != null ? updatedRental.getStartDate() : rental.getStartDate());
        rental.setEndDate(updatedRental.getEndDate() != null ? updatedRental.getEndDate() : rental.getEndDate());


        Car car = carRepository.findById(request.getCarId());
        rental.setCar(car != null ? car : rental.getCar());

        totalPrice = rentalBusinessRules.totalPriceForDateRange(request.getCarId(), request.getStartDate(), request.getEndDate());
        rental.setTotalPrice(totalPrice);

        User user = userRepository.findById(request.getUserId());
        rental.setUser(user != null ? user : rental.getUser());


        rentalBusinessRules.checkIfCarExists(request.getCarId());
        rentalBusinessRules.checkIfUserExists(request.getUserId());
        rentalBusinessRules.checkIfCarAvailable(request.getCarId(), request.getStartDate(), request.getEndDate());
        rentalBusinessRules.checkLocation(request.getCarId(), request.getUserId());
        totalPrice = rentalBusinessRules.totalPriceForDateRange(request.getCarId(), request.getStartDate(), request.getEndDate());

        rentalRepository.save(rental);

        UpdatedRentalResponse response = modelMapperService.forResponse().map(rental, UpdatedRentalResponse.class);

        return response;
    }

    @Override
    public Result deleteById(int id) {
        rentalRepository.deleteById(id);
        return new Result(true, "Rental Deleted!");
    }

    @Override
    public List<GetRentalByUserIdResponse> getRentalByUser(int id) {
        List<Rental> rentals = rentalRepository.findRentalsByUserId(id);
        List<GetRentalByUserIdResponse> response =  rentals.stream().map(rental -> modelMapperService.forResponse().map(rental, GetRentalByUserIdResponse.class)).collect(Collectors.toList());

        return response;
    }


}
