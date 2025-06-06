package org.anand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;



public enum RideStatus {
    REQUESTED("REQUESTED"),
    CONFIRMED("CONFIRMED"),
    DRIVER_EN_ROUTE("DRIVER_EN_ROUTE"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    private final String value;

    RideStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
