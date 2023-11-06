package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novi.cabforyou.enums.BookingStatus;
import com.novi.cabforyou.enums.CarType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue
    Long bookingId;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    private Trip trip;
//    @ManyToOne
//    private Invoice invoice;

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

    private double tripKmPriceMiniBus;

    private  double tripKmPriceCar;

    private double tripPrice;
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

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
        return this.tripDate;
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

//    public Invoice getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(Invoice invoice) {
//        this.invoice = invoice;
//    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}