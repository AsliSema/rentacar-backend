package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.enums.CarState;
import com.tobeto.rentacar.core.utilities.exceptions.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.dataAccess.abstracts.RentalRepository;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.Car;
import com.tobeto.rentacar.entities.concretes.Rental;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private UserRepository userRepository;
    private CarRepository carRepository;
    private RentalRepository rentalRepository;


    public void checkIfCarExists(int carId){
        Optional<Car> car = Optional.ofNullable(carRepository.findById(carId));

        if(!car.isPresent()){
            throw new BusinessException("Car is not exist!");
        }

    }

    public void checkIfUserExists(int userId){
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId));

        if(!user.isPresent()){
            throw new BusinessException("User is not exist!");
        }
    }

    public void checkLocation(int carId, int userId){
        Optional<Car> car = Optional.ofNullable(carRepository.findById(carId));
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId));

        System.out.println(car);
        System.out.println(user);

        if(!car.get().getLocation().equals(user.get().getCity())){
            throw new BusinessException("You are in a different place from the car!");
        }

    }


    public void checkIfCarAvailable(int carId, LocalDate startDate, LocalDate endDate){
        List<Rental> rentedCars = rentalRepository.getRentalsByCarIdAndDateRange(carId, startDate, endDate);

        if(!rentedCars.isEmpty()){
            throw new BusinessException("Car is not available between these days!");
        }

    }

    public double totalPriceForDateRange(int carId, LocalDate startDate, LocalDate endDate) {
        Car car = carRepository.findById(carId);
        double dailyPrice = car.getDailyPrice();
        double totalDays = (double) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return dailyPrice * totalDays;
    }

}
