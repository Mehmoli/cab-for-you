package com.novi.cabforyou.models;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="completedtrips")
public class CompletedTrips {

    //If the BookingStatus is COMPLETED save in CompletedTrips date time numberOfPersons from to km and price.

    //Completed trips needed for invoice...
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long completedTripId;

    @OneToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;

    public CompletedTrips() {
    }

    public Long getCompletedTripId() {
        return completedTripId;
    }

    public void setCompletedTripId(Long completedTripId) {
        this.completedTripId = completedTripId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public CompletedTrips(Long completedTripId, Booking booking) {
        this.completedTripId = completedTripId;
        this.booking = booking;
    }
}
