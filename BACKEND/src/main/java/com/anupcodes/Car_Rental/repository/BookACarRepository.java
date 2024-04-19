package com.anupcodes.Car_Rental.repository;

import com.anupcodes.Car_Rental.dto.BookACarDto;
import com.anupcodes.Car_Rental.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookACarRepository extends JpaRepository<BookACar, Long> {
    List<BookACar> findAllByUserId(long userId);
}
