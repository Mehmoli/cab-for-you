package com.novi.cabforyou.dtos;

import com.novi.cabforyou.models.Booking;
import com.novi.cabforyou.models.BookingStatus;
import com.novi.cabforyou.models.CarType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDto {

    public CustomerDto customer;
    public Long bookingId;

    public LocalDate tripDate;

    public LocalTime tripTime;

    //From
    public String fromStreet;
    public String fromHouseNumber;
    public String fromPostalCode;
    public String fromCity;


    //To
    public String toStreet;
    public String toHouseNumber;
    public String toPostalCode;
    public String toCity;

    public int numberOfPeople;

    public double distanceInKm;

    public double price;

    @Enumerated
    public CarType carType;

    @Enumerated
    public BookingStatus bookingStatus;

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

    public String getFromStreet() {
        return fromStreet;
    }

    public void setFromStreet(String fromStreet) {
        this.fromStreet = fromStreet;
    }

    public String getFromHouseNumber() {
        return fromHouseNumber;
    }

    public void setFromHouseNumber(String fromHouseNumber) {
        this.fromHouseNumber = fromHouseNumber;
    }

    public String getFromPostalCode() {
        return fromPostalCode;
    }

    public void setFromPostalCode(String fromPostalCode) {
        this.fromPostalCode = fromPostalCode;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToStreet() {
        return toStreet;
    }

    public void setToStreet(String toStreet) {
        this.toStreet = toStreet;
    }

    public String getToHouseNumber() {
        return toHouseNumber;
    }

    public void setToHouseNumber(String toHouseNumber) {
        this.toHouseNumber = toHouseNumber;
    }

    public String getToPostalCode() {
        return toPostalCode;
    }

    public void setToPostalCode(String toPostalCode) {
        this.toPostalCode = toPostalCode;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
