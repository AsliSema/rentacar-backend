package com.tobeto.rentacar.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CarState {
    AVAILABLE("Available"),
    RENTED("Rented"),
    MAINTENANCE("Maintenance");

    private final String state;
}
