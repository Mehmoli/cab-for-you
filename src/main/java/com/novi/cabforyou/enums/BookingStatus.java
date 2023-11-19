package com.novi.cabforyou.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingStatus {

    REQUEST("REQUEST"),
    CONFIRMED("CONFIRMED"),
    CANCELED("CANCELED"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED");

    private String value;

    BookingStatus(String value) {
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
