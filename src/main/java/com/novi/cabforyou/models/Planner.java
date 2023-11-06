package com.novi.cabforyou.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="planners")
public class Planner extends User {
    private String employeeNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "planner")
    List<Trip> trips;

    @JsonIgnore
    @OneToMany(mappedBy = "planner")
    List<BookingRequest> bookingRequests;

    public Planner() {
    }



    public Planner(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber, String employeeNumber, List<Trip> trips, List<BookingRequest> bookingRequests) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
        this.employeeNumber = employeeNumber;
        this.trips = trips;
        this.bookingRequests = bookingRequests;
    }

    public Planner(String employeeNumber, List<Trip> trips, List<BookingRequest> bookingRequests) {
        this.employeeNumber = employeeNumber;
        this.trips = trips;
        this.bookingRequests = bookingRequests;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<BookingRequest> getBookingRequests() {
        return bookingRequests;
    }

    public void setBookingRequests(List<BookingRequest> bookingRequests) {
        this.bookingRequests = bookingRequests;
    }

    public void addTrip(Trip tr) {
        //Todo
    }

    public void deleteTrip(Trip trip) {
        //Todo
    }
}
