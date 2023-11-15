package com.novi.cabforyou.dtos;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TripDto {
    public Long tripId;
    @NotNull(message = "Field cannot be null")
    @JsonIncludeProperties("username")
    public DriverDto driver;

    @NotNull(message = "Field cannot be null", groups = { OnCreateValidation.class, OnUpdateValidation.class })
    @Valid
    @JsonInclude
    @JsonIncludeProperties({"bookingId",
                            "tripDate",
                            "tripTime",
                            "fromAddress",
                            "toAddress",
                            "bookingStatus",
                            "carType"})
    public BookingRequestDto bookingRequest;

    public TripDto(Long tripId, DriverDto driver, BookingRequestDto bookingRequest) {
        this.tripId = tripId;
        this.driver = driver;
        this.bookingRequest = bookingRequest;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public DriverDto getDriver() {
        return driver;
    }

    public void setDriver(DriverDto driver) {
        this.driver = driver;
    }

    public BookingRequestDto getBookingRequest() {
        return bookingRequest;
    }

    public void setBookingRequests(BookingRequestDto bookingRequest) {
        this.bookingRequest = bookingRequest;
    }
}
