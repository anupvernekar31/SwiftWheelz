package com.anupcodes.Car_Rental.services.customer;

import com.anupcodes.Car_Rental.dto.BookACarDto;
import com.anupcodes.Car_Rental.dto.CarDto;
import com.anupcodes.Car_Rental.entity.BookACar;
import com.anupcodes.Car_Rental.entity.Car;
import com.anupcodes.Car_Rental.entity.User;
import com.anupcodes.Car_Rental.enums.BookCarStatus;
import com.anupcodes.Car_Rental.repository.BookACarRepository;
import com.anupcodes.Car_Rental.repository.CarRepository;
import com.anupcodes.Car_Rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookACarRepository bookACarRepository;

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());
        if (optionalCar.isPresent() && optionalUser.isPresent() && !optionalCar.get().isBooked()) {

            Car existingCar = optionalCar.get();
            existingCar.setBooked(true);
            BookACar bookACar = new BookACar();
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(existingCar);
            bookACar.setBookCarStatus(BookCarStatus.PENDING);
            long differenceInMillis = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long days = TimeUnit.MILLISECONDS.toDays(differenceInMillis);
            bookACar.setDays(days);
            bookACar.setPrice(Integer.parseInt(existingCar.getPrice()) * days);   // existingCar.getPrice()

            Date fromDate = new Date(bookACarDto.getFromDate().getTime());
            Date toDate = new Date(bookACarDto.getToDate().getTime());

            bookACar.setFromDate(fromDate);
            bookACar.setToDate(toDate);




            bookACarRepository.save(bookACar);
            carRepository.save(existingCar);
            return true;
        }
        return false;
    }

    @Override
    public CarDto getCarById(long carId) {

        Optional<Car> optionalCar = carRepository.findById(carId);
        return optionalCar.map(Car::getCarDto).orElse(null);

    }

    @Override
    public List<BookACarDto> getBookingsByUserId(long userId) {
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
    }


}
