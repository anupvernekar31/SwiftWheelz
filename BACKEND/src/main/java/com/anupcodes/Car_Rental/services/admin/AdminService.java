package com.anupcodes.Car_Rental.services.admin;

import com.anupcodes.Car_Rental.dto.BookACarDto;
import com.anupcodes.Car_Rental.dto.CarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    boolean updateCar(Long id, CarDto carDto);

    List<BookACarDto> getBookings();

    boolean changeBookinStatus(long bookingId, String status);

}
