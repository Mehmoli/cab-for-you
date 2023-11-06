package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CarType {

    SEDAN("SEDAN"),
    WAGON("WAGON"),
    MINIBUS("MINIBUS"),
    BIGBUS("BIGBUS");

    double kmPrice;

    public void setKmPrice(double kmPrice) {
        this.kmPrice = kmPrice;
    }
    
    public int seatsAvailable(){
        return switch (this) {
            case SEDAN -> 4;
            case WAGON -> 4;
            case MINIBUS -> 6;
            case BIGBUS -> 8;
            default -> 4;
        };
    }

    public double getPrice() {
        kmPrice = 2.65;
        return switch (this) {
            case SEDAN -> SEDAN.kmPrice *= 2.65;
            case WAGON -> WAGON.kmPrice *= 2.80;
            case MINIBUS -> MINIBUS.kmPrice *= 3.90;
            case BIGBUS -> BIGBUS.kmPrice *= 4.10;
            default -> kmPrice;
        };
    }

    private String value;
    CarType(String value){
        this.value = value;
    }
    

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    

}
