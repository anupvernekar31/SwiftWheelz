package com.anupcodes.Car_Rental.services.customer;

import com.anupcodes.Car_Rental.dto.BookACarDto;
import com.anupcodes.Car_Rental.dto.CarDto;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCarById(long carId);

    List<BookACarDto> getBookingsByUserId(long userId);
}
