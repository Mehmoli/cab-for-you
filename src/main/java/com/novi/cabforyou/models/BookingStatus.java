package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingStatus {

    ACCEPTED("ACCEPTED"),
    CANCELED("CANCELED"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED");

    private String value;
    BookingStatus(String value){
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
