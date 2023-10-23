package com.novi.cabforyou.dtos;

import com.novi.cabforyou.models.Authority;

import java.util.Set;

public class DriverDto {

    public String username;

    public String password;
    public String driverCallSign;

    public String email;
    public String driverPhone;
    public String licenceNumber;

    public Set<Authority> authorities;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverCallSign() {
        return driverCallSign;
    }

    public void setDriverCallSign(String driverCallSign) {
        this.driverCallSign = driverCallSign;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
