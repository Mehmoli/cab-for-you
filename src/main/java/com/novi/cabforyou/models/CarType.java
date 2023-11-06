package com.novi.cabforyou.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CarType {
    SEDAN("SEDAN", 2.65, 4),
    WAGON("WAGON", 2.80, 4),
    MINIBUS("MINIBUS", 3.90, 6),
    BIGBUS("BIGBUS", 4.10, 8);

    private double kmPrice;
    private int seatsAvailable;
    private final String value;

    private CarType(String value, double kmPrice, int seatsAvailable) {
        this.value = value;
        this.kmPrice = kmPrice;
        this.seatsAvailable = seatsAvailable;
    }

//    public int seatsAvailable() {
//        return switch (this) {
//            case SEDAN, WAGON -> 4;
//            case MINIBUS -> 6;
//            case BIGBUS -> 8;
//        };
//    }

    public double getPrice() {
        return kmPrice;
    }

    public void setKmPrice(double kmPrice) {
       this.kmPrice = kmPrice;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getValue() {
        return value;
    }


    public double setKmPrice() {
        return this.kmPrice = kmPrice;
    }
}

