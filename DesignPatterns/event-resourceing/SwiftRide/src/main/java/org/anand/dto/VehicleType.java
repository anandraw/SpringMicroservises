package org.anand.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


public enum VehicleType {

    BIKE(1, 10),
    Car(5, 30),
    AUTO_RICKSHAW(3, 15);

    private final int capacity;
    private final double baseFare;
    VehicleType(int capacity, double baseFare) {
        this.capacity = capacity;
        this.baseFare = baseFare;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getBaseFare() {
        return baseFare;
    }
}
