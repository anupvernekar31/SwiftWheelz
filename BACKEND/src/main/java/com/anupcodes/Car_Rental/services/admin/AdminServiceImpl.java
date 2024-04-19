package com.anupcodes.Car_Rental.services.admin;

import com.anupcodes.Car_Rental.dto.BookACarDto;
import com.anupcodes.Car_Rental.dto.CarDto;
import com.anupcodes.Car_Rental.entity.BookACar;
import com.anupcodes.Car_Rental.entity.Car;
import com.anupcodes.Car_Rental.enums.BookCarStatus;
import com.anupcodes.Car_Rental.repository.BookACarRepository;
import com.anupcodes.Car_Rental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CarRepository carRepository;

    private final BookACarRepository bookACarRepository;

    @Override
    public boolean postCar(CarDto carDto) throws IOException {
        try {
            Car car = new Car();
            car.setBrand(carDto.getBrand());
            car.setColour(carDto.getColour());
            car.setName(carDto.getName());
            car.setType(carDto.getType());
            car.setTransmission(carDto.getTransmission());
            car.setDescription(carDto.getDescription());
            car.setPrice(carDto.getPrice());
            car.setYear(carDto.getYear());
            //car.setImage(carDto.getImage().getBytes());
            car.setImage(carDto.getImage());
            car.setFavourite(carDto.isFavourite());
            carRepository.save(car);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public boolean updateCar(Long carId, CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if(optionalCar.isPresent()){
            Car existingCar = optionalCar.get();
//            if(carDto.getImage() != null){
//                existingCar.setImage(carDto.getImage());
//            }
            existingCar.setImage(carDto.getImage());
            existingCar.setBrand(carDto.getBrand());
            existingCar.setName(carDto.getName());
            existingCar.setTransmission(carDto.getTransmission());
            existingCar.setColour(carDto.getColour());;
            existingCar.setPrice(carDto.getPrice());
            existingCar.setDescription(carDto.getDescription());
            existingCar.setYear(carDto.getYear());
            existingCar.setFavourite(carDto.isFavourite());
            carRepository.save(existingCar);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BookACarDto> getBookings() {
        return bookACarRepository.findAll().stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
    }

    @Override
    public boolean changeBookinStatus(long bookingId, String status) {

        Optional<BookACar> optionalBookACar = bookACarRepository.findById(bookingId);
        if(optionalBookACar.isPresent()){
            BookACar existingBookACar = optionalBookACar.get();
            Car car = existingBookACar.getCar();
            long carId = car.getId();
            // Optional<Car> car = carRepository.findById(bookingId);


            if(Objects.equals(status,"Approved")){
                existingBookACar.setBookCarStatus(BookCarStatus.APPROVED);
            } else if(Objects.equals(status,"Rejected")) {
                existingBookACar.setBookCarStatus(BookCarStatus.REJECTED);
            } else {
                existingBookACar.setBookCarStatus(BookCarStatus.COMPLETED);
                car.setBooked(false);
            }
            bookACarRepository.save(existingBookACar);
            return true;
        }
        return false;
    }
}
