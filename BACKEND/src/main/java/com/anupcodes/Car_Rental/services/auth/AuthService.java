package com.anupcodes.Car_Rental.services.auth;

import com.anupcodes.Car_Rental.dto.SignupRequest;
import com.anupcodes.Car_Rental.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
