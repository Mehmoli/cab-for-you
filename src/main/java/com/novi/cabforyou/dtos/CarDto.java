package com.novi.cabforyou.dtos;

import com.novi.cabforyou.models.CarType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;

public class CarDto {

    public Long carId;

    public String make;

    public String model;
    @Max(value = 8, message = "We have no cars for more than 8 passengers")
    public int availableSeats;

    public String licensePlate;

    @Enumerated(EnumType.STRING)
    public CarType carType;

    public CarDto(Long carId, String make, String model, int availableSeats, String licensePlate, CarType carType) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.availableSeats = availableSeats;
        this.licensePlate = licensePlate;
        this.carType = carType;
    }

    public CarDto() {

    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

}
