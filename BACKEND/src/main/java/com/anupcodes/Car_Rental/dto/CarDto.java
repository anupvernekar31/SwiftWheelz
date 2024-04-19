package com.anupcodes.Car_Rental.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class CarDto {
    private Long id;
    private String brand;
    private String colour;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private String price;
    private String year;
    // private MultipartFile image;
    private String image;
    private  byte[] returnedImage;
    private boolean favourite;
    private boolean booked;
}
