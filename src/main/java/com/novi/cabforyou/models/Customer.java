package com.novi.cabforyou.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer extends User{

    private String address;
    private int customerPhone;

    @OneToMany(
            targetEntity = Booking.class,
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Booking> bookings = new HashSet<>();


    @OneToMany
    private Set<Feedback> feedbacks = new HashSet<>();


    public Customer() {}

    public Customer(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber, String address, int customerPhone, Set<Booking> bookings, Set<Feedback> feedbacks) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
        this.address = address;
        this.customerPhone = customerPhone;
        this.bookings = bookings;
        this.feedbacks = feedbacks;
    }

    public Customer(String address, int customerPhone, Set<Booking> bookings, Set<Feedback> feedbacks) {
        this.address = address;
        this.customerPhone = customerPhone;
        this.bookings = bookings;
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

    public int getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(int customerPhone) {
        this.customerPhone = customerPhone;
    }

}
