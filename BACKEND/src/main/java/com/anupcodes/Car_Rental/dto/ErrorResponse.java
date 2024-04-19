package com.anupcodes.Car_Rental.dto;

import com.anupcodes.Car_Rental.enums.UserRole;
import lombok.Data;

@Data
public class ErrorResponse {
    private int errorCode;
    private String message;
}