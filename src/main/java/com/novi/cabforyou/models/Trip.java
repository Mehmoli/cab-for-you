package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue
    Long tripId;
    @JsonIgnore
    @ManyToOne
    Driver driver;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trip")
    List<BookingRequest> bookingRequests;


    public Trip(Long tripId, Driver driver, List<BookingRequest> bookingRequests) {
        this.tripId = tripId;
        this.driver = driver;
        this.bookingRequests = bookingRequests;
    }

    public Trip() {
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<BookingRequest> getBookingRequests() {
        return bookingRequests;
    }

    public void setBookingRequests(List<BookingRequest> bookingRequests) {
        this.bookingRequests = bookingRequests;
    }
}


