package com.umerscode.employeemanager.Controller;

import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
public class Response {

    private LocalDate timestamp;
    private HttpStatus httpStatus;
    private int statusCode;
    private String message;
    private List<?> data;
}
