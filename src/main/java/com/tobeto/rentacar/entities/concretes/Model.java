package com.tobeto.rentacar.entities.concretes;

import com.tobeto.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="models")
public class Model extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="brandId")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="fuelId")
    private Fuel fuel;

    @ManyToOne
    @JoinColumn(name="transmissionId")
    private Transmission transmission;

    @OneToMany(mappedBy = "model")
    private List<Car> cars;



    @Override
    public String toString() {
        return "Model(name=" + this.getName() + ", brand=" + (this.getBrand() != null ? this.getBrand().getId() : "null") + ", fuel=" + (this.getFuel() != null ? this.getFuel().getId() : "null") + ", transmission=" + (this.getTransmission() != null ? this.getTransmission().getId() : "null") + ", cars=" + this.getCars() + ")";
    }


}
