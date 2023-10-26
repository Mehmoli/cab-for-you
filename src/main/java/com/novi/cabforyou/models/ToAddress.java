package com.novi.cabforyou.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class ToAddress {
    @NotNull
    @Size(max = 100)
    private String toStreet;

    @NotNull
    @Size(max = 10)
    private String toHouseNumber;

    @NotNull
    @Size(max = 6)
    private String toPostalCode;

    @NotNull
    @Size(max = 50)
    private String toCity;

    public ToAddress(){
    }

    public ToAddress(String toStreet, String toHouseNumber, String toPostalCode, String toCity) {
        this.toStreet = toStreet;
        this.toHouseNumber = toHouseNumber;
        this.toPostalCode = toPostalCode;
        this.toCity = toCity;
    }

    public String getToStreet() {
        return toStreet;
    }

    public void setToStreet(String toStreet) {
        this.toStreet = toStreet;
    }

    public String getToHouseNumber() {
        return toHouseNumber;
    }

    public void setToHouseNumber(String toHouseNumber) {
        this.toHouseNumber = toHouseNumber;
    }

    public String getToPostalCode() {
        return toPostalCode;
    }

    public void setToPostalCode(String toPostalCode) {
        this.toPostalCode = toPostalCode;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
}
