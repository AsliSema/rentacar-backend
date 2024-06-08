package com.tobeto.rentacar.business.dtos.requests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBrandRequest {

    @Size(min=2, max=15)
    private String name;
}
