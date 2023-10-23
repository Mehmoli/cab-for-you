package com.novi.cabforyou.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="drivers")
public class Driver extends User {

    private String driverCallSign;

    private String driverPhone;

    private String licenceNumber;

    @ManyToMany(mappedBy = "drivers")
    List<Car> cars = new ArrayList<>();

    public Driver() {}

    public Driver(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber, String driverCallSign, String driverPhone, String licenceNumber, List<Car> cars) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
        this.driverCallSign = driverCallSign;
        this.driverPhone = driverPhone;
        this.licenceNumber = licenceNumber;
        this.cars = cars;
    }

    public Driver(String driverCallSign, String driverPhone, String licenceNumber, List<Car> cars) {
        this.driverCallSign = driverCallSign;
        this.driverPhone = driverPhone;
        this.licenceNumber = licenceNumber;
        this.cars = cars;
    }

    public String getDriverCallSign() {
        return driverCallSign;
    }

    public void setDriverCallSign(String driverCallSign) {
        this.driverCallSign = driverCallSign;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
