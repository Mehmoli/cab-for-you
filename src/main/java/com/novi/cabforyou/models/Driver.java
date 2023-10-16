package com.novi.cabforyou.models;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="drivers")
public class Driver extends User {

    private String driverName;

    private String licenceNumber;

    public Driver() {}

    public Driver(String username, String password, Set<Authority> authorities, String email, String driverName, String licenceNumber) {
        super(username, password, authorities, email);
        this.driverName = driverName;
        this.licenceNumber = licenceNumber;
    }

    public Driver(String driverName, String licenceNumber) {
        this.driverName = driverName;
        this.licenceNumber = licenceNumber;
    }
}
