package com.novi.cabforyou.dtos;

import com.novi.cabforyou.models.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDto {

    public Long bookingId;

    public LocalDate tripDate;

    public LocalTime tripTime;
    //From
    public FromAddress fromAddress;
    //To
    public ToAddress toAddress;
    public int numberOfPeople;

    public double distanceInKm;

    public double tripKmPriceMiniBus = 4.20;

    public double tripKmPriceCar = 2.60;
    public double tripPrice;

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }

    @Enumerated
    public CarType carType;

    @Enumerated
    public BookingStatus bookingStatus;


    public double getTripKmPriceMiniBus() {
        return tripKmPriceMiniBus;
    }

    public void setTripKmPriceMiniBus(double tripKmPriceMiniBus) {
        this.tripKmPriceMiniBus = tripKmPriceMiniBus;
    }

    public double getTripKmPriceCar() {
        return tripKmPriceCar;
    }

    public void setTripKmPriceCar(double tripKmPriceCar) {
        this.tripKmPriceCar = tripKmPriceCar;
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
