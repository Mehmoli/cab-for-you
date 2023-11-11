package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="drivers")
public class Driver extends User {

    private String licenceNumber;
    @OneToOne
    @JoinColumn(name="car_id")
    private Car car;

    @OneToMany(
            mappedBy = "driver",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Trip> trips;

    public Driver() {}

    public Driver(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber, String licenceNumber, Car car) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
        this.licenceNumber = licenceNumber;
        this.car = car;
    }

    public Driver(String licenceNumber, Car car) {
        this.licenceNumber = licenceNumber;
        this.car = car;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {this.trips.add(trip);}

    public void deleteTrip(Trip trip) {this.trips.remove(trip);}

}
