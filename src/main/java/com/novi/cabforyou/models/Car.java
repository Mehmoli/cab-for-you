package com.novi.cabforyou.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cabs")
public class Cab {

    @Id
    @GeneratedValue
    Long cabId;

    private String make;

    private String model;

    private int availableSeats;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    CarType carType = CarType.WAGON; //default value.

    @OneToMany(
            mappedBy = "cab",
            fetch = FetchType.LAZY
    )
    List<Trip> trips;

    @ManyToMany
    List<Driver> drivers = new ArrayList<>();

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

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
