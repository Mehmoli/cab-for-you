package com.novi.cabforyou.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer extends User{

    private String address;

    @OneToMany(
            targetEntity = BookingRequest.class,
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<BookingRequest> bookingRequests = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id", referencedColumnName = "feedbackId")
    private Feedback feedbacks;

    public Customer() {}

    public Customer(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber, String address, List<BookingRequest> bookingRequests, Feedback feedbacks) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
        this.address = address;
        this.bookingRequests = bookingRequests;
        this.feedbacks = feedbacks;
    }

    public Customer(String address, List<BookingRequest> bookingRequests, Feedback feedbacks) {
        this.address = address;
        this.bookingRequests = bookingRequests;
        this.feedbacks = feedbacks;
    }

    public Customer(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
