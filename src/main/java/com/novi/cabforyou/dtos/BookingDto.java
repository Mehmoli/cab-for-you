package com.novi.cabforyou.dtos;

import com.novi.cabforyou.models.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDto {

    public CustomerDto customer;
    public Long bookingId;

    public LocalDate tripDate;

    public LocalTime tripTime;
    //From
    public FromAddress fromAddress;
    //To
    public ToAddress toAddress;
    public int numberOfPeople;

    public double distanceInKm;

    public double tripKmPrice;

    @Enumerated
    public CarType carType;

    @Enumerated
    public BookingStatus bookingStatus;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public LocalTime getTripTime() {
        return tripTime;
    }

    public void setTripTime(LocalTime tripTime) {
        this.tripTime = tripTime;
    }

    public FromAddress getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(FromAddress fromAddress) {
        this.fromAddress = fromAddress;
    }

    public ToAddress getToAddress() {
        return toAddress;
    }

    public void setToAddress(ToAddress toAddress) {
        this.toAddress = toAddress;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }


    public double getTripKmPrice() {
        return tripKmPrice;
    }

    public void setTripKmPrice(double tripKmPrice) {
        this.tripKmPrice = tripKmPrice;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
