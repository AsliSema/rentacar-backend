package com.tobeto.rentacar.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelResponse {
    private int id;
    private String name;
    private int brandId;
    private int fuelId;
    private int transmissionId;
    private LocalDateTime createdDate;
    private String fuelName;
    private String transmissionName;
    private String brandName;
}
