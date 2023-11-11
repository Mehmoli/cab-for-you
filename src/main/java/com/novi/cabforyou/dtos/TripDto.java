package com.novi.cabforyou.dtos;

import com.fasterxml.jackson.annotation.*;
import com.novi.cabforyou.models.*;

import java.util.List;

public class TripDto {
    public Long tripId;
    @JsonIncludeProperties("username")
    public Driver driver;
    @JsonInclude
    @JsonIncludeProperties({"bookingId",
            "tripDate",
            "tripTime",
            "fromAddress",
            "toAddress",
            "bookingStatus",
            "carType"})
    public List<BookingRequest> bookingRequests;

    public TripDto(Long tripId, Driver driver, List<BookingRequest> bookingRequests) {
        this.tripId = tripId;
        this.driver = driver;
        this.bookingRequests = bookingRequests;
    }

    public TripDto() {
    }

    public static TripDto transferToTripDto(Trip trip) {
        TripDto tripDto = new TripDto();
        tripDto.tripId = trip.getTripId();
        tripDto.driver = trip.getDriver();
        tripDto.bookingRequests = trip.getBookingRequests();
        return tripDto;
    }

    public Trip transferToTrip() {

        Trip trip = new Trip();

        trip.setDriver(driver);
        trip.setBookingRequests(bookingRequests);

        return trip;
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
