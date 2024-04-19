package com.anupcodes.Car_Rental.dto;

import com.anupcodes.Car_Rental.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private UserRole userRole;
}