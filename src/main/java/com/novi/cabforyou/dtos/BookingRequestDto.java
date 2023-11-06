package com.novi.cabforyou.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.novi.cabforyou.models.BookingStatus;
import com.novi.cabforyou.models.CarType;
import com.novi.cabforyou.models.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequestDto {

    public Long bookingId;

    public LocalDate tripDate;

    public LocalTime tripTime;
    //From
    public FromAddress fromAddress;
    //To
    public ToAddress toAddress;

    public int numberOfPeople;

    public double distanceInKm;
    public double kmPrice;

    public double tripPrice;

    @JsonIncludeProperties("id")
    public Customer customer;
    @JsonIncludeProperties("id")
    private Planner planner;
    @JsonIncludeProperties("id")
    public Trip trip;

    @Enumerated
    public CarType carType;

    @Enumerated
    public BookingStatus bookingStatus;

    @JsonIgnore
    public  double selectedCarType = CarType.SEDAN.getPrice();

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
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

    public double getKmPrice() {
        return kmPrice;
    }

    public void setKmPrice(double kmPrice) {
        this.kmPrice = kmPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Planner getPlanner() {
        return planner;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
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

    public double getSelectedCarType() {
        return selectedCarType;
    }

    public void setSelectedCarType(double selectedCarType) {
        this.selectedCarType = selectedCarType;
    }

    public BookingRequest transferToBooking() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setTripDate(tripDate);
        bookingRequest.setTripTime(tripTime);
        bookingRequest.setNumberOfPeople(numberOfPeople);
        bookingRequest.setFromAddress(fromAddress);
        bookingRequest.setToAddress(toAddress);
        bookingRequest.setDistanceInKm(distanceInKm);
        bookingRequest.setTripPrice(tripPrice);
        bookingRequest.setTrip(trip);

        return bookingRequest;
    }

}