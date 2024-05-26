package com.tobeto.rentacar.entities.concretes;

import com.tobeto.rentacar.core.entities.BaseEntity;
import com.tobeto.rentacar.core.enums.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="cars")
public class Car extends BaseEntity {

    @Column(name="modelYear")
    private int modelYear;

    @Column(name="plate")
    private String plate;

    @Column(name="kilometer")
    private int kilometer;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private CarState state = CarState.AVAILABLE;

    @Column(name="dailyPrice")
    private double dailyPrice;

    @Column(name = "color")
    private String color;

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name="location")
    private String location;

    @ManyToOne
    @JoinColumn(name="modelId")
    private Model model;


    public String toString() {
        return "Car(modelYear=" + this.getModelYear() + ", plate=" + this.getPlate() + ", kilometer=" + this.getKilometer() + ", state=" + this.getState() + ", dailyPrice=" + this.getDailyPrice() + ", color=" + this.getColor() + ", location=" + this.getLocation() + ", model=" + (this.getModel() != null ? this.getModel().getId() : "null") + ")";
    }

}
