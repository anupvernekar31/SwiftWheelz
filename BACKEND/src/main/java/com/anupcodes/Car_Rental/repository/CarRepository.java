package com.anupcodes.Car_Rental.repository;

import com.anupcodes.Car_Rental.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


}
