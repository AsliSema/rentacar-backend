package com.tobeto.rentacar.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum LicenseClass {
    B("B"),
    C("C"),
    D("D"),
    E("E");

    private final String licenseClass;

}
