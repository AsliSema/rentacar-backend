package com.tobeto.rentacar.entities.concretes;

import com.tobeto.rentacar.core.entities.BaseEntity;
import com.tobeto.rentacar.core.enums.LicenseClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="licenses")
public class License extends BaseEntity {

    @Column(name = "licenseNumber")
    private String licenseNumber;

    @Column(name = "issueDate")
    private LocalDateTime issueDate;

    @Column(name = "licenseClass")
    @Enumerated(EnumType.STRING)
    private LicenseClass licenseClass;

    @OneToOne(mappedBy = "license")
    private User user;

}
