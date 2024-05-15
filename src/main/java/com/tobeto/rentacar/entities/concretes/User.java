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
@Table(name="users")
public class User extends BaseEntity {

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "confirmPassword")
    private String confirmPassword;

    @Column(name = "companyName")
    private String companyName;

    @Column(name= "phoneNumber")
    private String phoneNumber;

    @Column(name="identityNumber")
    private String identityNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "role")
    private String role="user";

    @OneToOne
    @JoinColumn(name= "licenseId")
    private License license;


}
