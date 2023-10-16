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
    private int customerPhone;

    @OneToMany(
            targetEntity = Booking.class,
            mappedBy = "bookingId",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Booking> bookings = new HashSet<>();


    @OneToMany
    private Set<Feedback> feedbacks = new HashSet<>();


    public Customer() {}

    public Customer(String address, int customerPhone) {
        this.address = address;
        this.customerPhone = customerPhone;
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
