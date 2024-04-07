package com.tobeto.rentacar.core.utilities.exceptions.problemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetails {
    //rfce standarts

    private String title;
    private String detail;
    private String Status;
    private String Type;
}
