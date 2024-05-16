package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.abstracts.UserService;
import com.tobeto.rentacar.business.dtos.responses.GetCarResponse;
import com.tobeto.rentacar.business.dtos.responses.GetUserResponse;
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
    private CarService carService;
    private UserService userService;


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

        GetCarResponse car = carService.get(carId);
        GetUserResponse user = userService.get(userId);


        if(!car.getLocation().equals(user.getCity())){
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
        //Car car = carRepository.findById(carId);
        GetCarResponse car = carService.get(carId);
        double dailyPrice = car.getDailyPrice();
        double totalDays = (double) ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return dailyPrice * totalDays;
    }


    public void checkIfUserDoesntHaveLicense(int userId){
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId));

        if((user.get().getLicense() == null)){
            throw new BusinessException("It seems you don't have a driver's license! If you do, you should update your license!");
        }

    }

    public void checkUserLicenseYear(int userId){
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId));

        int totalYears = (int) ChronoUnit.YEARS.between(user.get().getLicense().getIssueDate(), LocalDate.now());


        if(totalYears < 1){
            throw new BusinessException("If you have had a driver's license for less than one year, you cannot rent a car!");
        }

    }
}
