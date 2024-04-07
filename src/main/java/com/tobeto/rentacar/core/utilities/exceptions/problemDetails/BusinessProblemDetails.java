package com.tobeto.rentacar.core.utilities.exceptions.problemDetails;

import org.springframework.http.HttpStatus;

public class BusinessProblemDetails extends ProblemDetails{
    public BusinessProblemDetails() {
        setTitle("Business Rule Violation");
        setType("https://mydomain.com/exceptions/business");
        setStatus(HttpStatus.BAD_REQUEST.toString());
    }
}
