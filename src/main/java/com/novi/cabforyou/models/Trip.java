package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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

    @OneToOne
    BookingRequest bookingRequest;


    public Trip(Long tripId, Driver driver, List<BookingRequest> bookingRequests) {
        this.tripId = tripId;
        this.driver = driver;
        this.bookingRequest = bookingRequest;
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

    public BookingRequest getBookingRequest() {
        return bookingRequest;
    }

    public void setBookingRequest(BookingRequest bookingRequest) {
        this.bookingRequest = bookingRequest;
    }

}


