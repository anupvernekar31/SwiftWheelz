package com.anupcodes.Car_Rental.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Data
public class SignupRequest {
    private String email;
    private String name;
    private String password;
}
