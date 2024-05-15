package com.tobeto.rentacar.entities.concretes;


import com.tobeto.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="rentals")
public class Rental extends BaseEntity {

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "totalPrice")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name="CarId")
    private Car car;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;


}
