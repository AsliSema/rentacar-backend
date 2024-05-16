package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.concretes.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Integer> {

    License findById(int id);

}
