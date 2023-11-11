package com.novi.cabforyou.models;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "planners")
public class Planner extends User {
    private String employeeNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planner")
    List<BookingRequest> bookingRequests;

    public Planner() {
    }

    public Planner(String username, String password, Set<Authority> authorities, String email, String firstName, String lastName, String phoneNumber, String employeeNumber, List<BookingRequest> bookingRequests) {
        super(username, password, authorities, email, firstName, lastName, phoneNumber);
        this.employeeNumber = employeeNumber;
        this.bookingRequests = bookingRequests;
    }

    public Planner(String employeeNumber, List<BookingRequest> bookingRequests) {
        this.employeeNumber = employeeNumber;
        this.bookingRequests = bookingRequests;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<BookingRequest> getBookingRequests() {
        return bookingRequests;
    }

    public void setBookingRequests(List<BookingRequest> bookingRequests) {
        this.bookingRequests = bookingRequests;
    }

}
