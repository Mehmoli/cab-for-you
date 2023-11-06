package com.novi.cabforyou.dtos;

import com.novi.cabforyou.models.CarType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class CabDto {

    public Long cabId;

    public String make;

    public String model;

    public int availableSeats;

    public String licensePlate;

    @Enumerated(EnumType.STRING)
    public CarType carType;

    public Long getCabId() {
        return cabId;
    }

    public void setCabId(Long cabId) {
        this.cabId = cabId;
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

    public CarType getCabType() {
        return carType;
    }

    public void setCabType(CarType carType) {
        this.carType = carType;
    }
}
