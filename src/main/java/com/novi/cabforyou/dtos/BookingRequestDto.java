package com.novi.cabforyou.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.novi.cabforyou.models.BookingStatus;
import com.novi.cabforyou.models.CarType;
import com.novi.cabforyou.models.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequestDto {

    @NotNull(message = "Field cannot be null", groups = OnUpdateValidation.class)
    public Long bookingId;

    public LocalDate tripDate;

    public LocalTime tripTime;

    public FromAddress fromAddress;

    public ToAddress toAddress;

    public int numberOfPeople;

    public double distanceInKm;
    public double kmPrice;

    public double tripPrice;

    @JsonIncludeProperties("username")
    public CustomerDto customer;

    @JsonIncludeProperties("username")
    public PlannerDto planner;

    @JsonIncludeProperties("id")
    public TripDto trip;

    @Enumerated
    public CarType carType;

    @Enumerated
    public BookingStatus bookingStatus;

    @JsonIgnore
    public double selectedCarType = CarType.SEDAN.getPrice();

    public BookingRequestDto(Long bookingId, LocalDate tripDate, LocalTime tripTime, FromAddress fromAddress, ToAddress toAddress, int numberOfPeople, double distanceInKm, double kmPrice, double tripPrice, CustomerDto customer, PlannerDto planner, TripDto trip, CarType carType, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.tripDate = tripDate;
        this.tripTime = tripTime;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.numberOfPeople = numberOfPeople;
        this.distanceInKm = distanceInKm;
        this.kmPrice = kmPrice;
        this.tripPrice = tripPrice;
        this.customer = customer;
        this.planner = planner;
        this.trip = trip;
        this.carType = carType;
        this.bookingStatus = bookingStatus;
    }

    public BookingRequestDto() {
    }

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

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public PlannerDto getPlanner() {
        return planner;
    }

    public void setPlanner(PlannerDto planner) {
        this.planner = planner;
    }

    public TripDto getTrip() {
        return trip;
    }

    public void setTrip(TripDto trip) {
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

    public static BookingRequestDto transferToBookingRequestDto(BookingRequest bookingRequest) {

        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.bookingId = bookingRequest.getBookingId();
        bookingRequestDto.tripDate = bookingRequest.getTripDate();
        bookingRequestDto.tripTime = bookingRequest.getTripTime();
        bookingRequestDto.bookingStatus = bookingRequest.getBookingStatus();
        bookingRequestDto.fromAddress = bookingRequest.getFromAddress();
        bookingRequestDto.toAddress = bookingRequest.getToAddress();
        bookingRequestDto.carType = bookingRequest.getCarType();
        bookingRequestDto.numberOfPeople = bookingRequest.getNumberOfPeople();
        bookingRequestDto.distanceInKm = bookingRequest.getDistanceInKm();
        bookingRequestDto.kmPrice = bookingRequest.getKmPrice();
        bookingRequestDto.tripPrice = bookingRequest.getTripPrice();
        return bookingRequestDto;
    }
}