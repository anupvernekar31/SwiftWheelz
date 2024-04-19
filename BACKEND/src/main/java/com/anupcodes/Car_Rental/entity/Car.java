package com.anupcodes.Car_Rental.entity;

import com.anupcodes.Car_Rental.dto.CarDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String colour;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private String price;
    private String year;
//    @Column(columnDefinition = "longblob")
//    private byte[] image;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String image;
    private boolean favourite;
    private boolean booked;

    public CarDto getCarDto(){
        CarDto carDto = new CarDto();
        carDto.setId(id);
        carDto.setName(name);
        carDto.setBrand(brand);
        carDto.setColour(colour);
        carDto.setType(type);
        carDto.setTransmission(transmission);
        carDto.setDescription(description);
        carDto.setPrice(price);
        carDto.setYear(year);
        // carDto.setReturnedImage(image);
        carDto.setImage(image);
        carDto.setFavourite(favourite);
        carDto.setBooked(booked);
        return carDto;


    }
}
