package org.anand.dto;

public enum RideType {
    NORMAL("NORMAL"),
    CARPOOL("CARPOOL");

    private final String value;

    RideType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
