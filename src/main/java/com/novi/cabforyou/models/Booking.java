package com.novi.cabforyou.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue
    Long bookingId;

    @ManyToOne
    private Customer customers;

    @OneToOne
    private CompletedTrips completedTrip;

    private LocalDate tripDate;

    private LocalTime tripTime;

    private String fromAdress;

    private String toDestination;

    private int numberOfPeople;

    private int distanceInKm;

    private int price;

    private CarType carType;

    private BookingStatus bookingStatus;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customers) {
        this.customers = customers;
    }

    public CompletedTrips getCompletedTrip() {
        return completedTrip;
    }

    public void setCompletedTrip(CompletedTrips completedTrip) {
        this.completedTrip = completedTrip;
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

    public String getFromAdress() {
        return fromAdress;
    }

    public void setFromAdress(String fromAdress) {
        this.fromAdress = fromAdress;
    }

    public String getToDestination() {
        return toDestination;
    }

    public void setToDestination(String toDestination) {
        this.toDestination = toDestination;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
