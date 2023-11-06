package com.novi.cabforyou.models;

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

