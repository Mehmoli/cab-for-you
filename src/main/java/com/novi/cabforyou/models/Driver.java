package com.novi.cabforyou.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="drivers")
public class Driver extends User {

    private String driverName = getFirstName()+ " " + getLastName();

    private String licenceNumber;

    @ManyToMany(mappedBy = "drivers")
    List<Car> cars = new ArrayList<>();

    public Driver() {}

    public Driver(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber, String driverName, String licenceNumber, List<Car> cars) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
        this.driverName = driverName;
        this.licenceNumber = licenceNumber;
        this.cars = cars;
    }

    public Driver(String driverName, String licenceNumber, List<Car> cars) {
        this.driverName = driverName;
        this.licenceNumber = licenceNumber;
        this.cars = cars;
    }

    public Driver(String driverName, String licenceNumber) {
        this.driverName = driverName;
        this.licenceNumber = licenceNumber;
    }
}
