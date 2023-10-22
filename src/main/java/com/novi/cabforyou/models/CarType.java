package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CarType {

    SEDAN("SEDAN"),
    WAGON("WAGON"),
    MINIBUS("MINIBUS");

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
