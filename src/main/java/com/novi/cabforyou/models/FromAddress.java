package com.novi.cabforyou.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class FromAddress {
    @NotNull
    @Size(max = 100)
    private String fromStreet;

    @NotNull
    @Size(max = 10)
    private String fromHouseNumber;

    @NotNull
    @Size(max = 6)
    private String fromPostalCode;

    @NotNull
    @Size(max = 50)
    private String fromCity;


    public FromAddress() {

    }

    public FromAddress(String fromStreet, String fromHouseNumber, String fromPostalCode, String fromCity) {
        this.fromStreet = fromStreet;
        this.fromHouseNumber = fromHouseNumber;
        this.fromPostalCode = fromPostalCode;
        this.fromCity = fromCity;
    }

    public String getFromStreet() {
        return fromStreet;
    }

    public void setFromStreet(String fromStreet) {
        this.fromStreet = fromStreet;
    }

    public String getFromHouseNumber() {
        return fromHouseNumber;
    }

    public void setFromHouseNumber(String fromHouseNumber) {
        this.fromHouseNumber = fromHouseNumber;
    }

    public String getFromPostalCode() {
        return fromPostalCode;
    }

    public void setFromPostalCode(String fromPostalCode) {
        this.fromPostalCode = fromPostalCode;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }
}
