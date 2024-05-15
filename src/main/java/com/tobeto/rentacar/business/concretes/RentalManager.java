package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.dtos.requests.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.CreatedRentalResponse;
import com.tobeto.rentacar.business.rules.RentalBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.RentalRepository;
import com.tobeto.rentacar.entities.concretes.Model;
import com.tobeto.rentacar.entities.concretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {

    private ModelMapperService modelMapperService;
    private RentalRepository rentalRepository;
    private RentalBusinessRules rentalBusinessRules;

    @Override
    public CreatedRentalResponse add(CreateRentalRequest request) {

        double totalPrice;

        rentalBusinessRules.checkIfCarExists(request.getCarId());
        rentalBusinessRules.checkIfUserExists(request.getUserId());
        //rentalBusinessRules.checkIfCarStateAvailable(request.getCarId());
        rentalBusinessRules.checkIfCarAvailable();
        totalPrice = rentalBusinessRules.totalPriceForDateRange(request.getCarId(), request.getStartDate(), request.getEndDate());



        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);

        rental.setTotalPrice(totalPrice);
        rental.setCreatedDate(LocalDateTime.now());
        Rental createdRental = this.rentalRepository.save(rental);

        CreatedRentalResponse createdRentalResponse = this.modelMapperService.forResponse().map(createdRental, CreatedRentalResponse.class);
        return createdRentalResponse;

    }
}
