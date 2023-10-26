package com.novi.cabforyou.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue
    Long bookingId;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private CompletedTrips completedTrip;

    private LocalDate tripDate;

    private LocalTime tripTime;

    //From
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "fromStreet", column = @Column(name = "from_street")),
            @AttributeOverride(name = "fromHouseNumber", column = @Column(name = "from_hnumber")),
            @AttributeOverride(name = "fromPostalCode", column = @Column(name = "from_pcode")),
            @AttributeOverride(name = "fromCity", column = @Column(name = "from_city"))
    })
    private FromAddress fromAddress;

    //To
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "toStreet", column = @Column(name = "to_street")),
            @AttributeOverride(name = "toHouseNumber", column = @Column(name = "to_hnumber")),
            @AttributeOverride(name = "toPostalCode", column = @Column(name = "to_pcode")),
            @AttributeOverride(name = "toCity", column = @Column(name = "to_city"))
    })
    private ToAddress toAddress;

    private int numberOfPeople;

    private double distanceInKm;

    private double tripKmPrice;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public double calculateTripPrice(){
        return this.distanceInKm * this.distanceInKm;
    }

}
