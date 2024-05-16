package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    Optional<Model> findByNameIgnoreCase(String name);

    Model findById(int id);

    List<Model> findByBrandId(int id);

    List<Model> findByFuelId(int id);

    List<Model> findByTransmissionId(int id);


}
