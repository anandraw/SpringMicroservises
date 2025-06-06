package org.anand.fare;

import org.anand.dto.RideType;

public interface FareCalculator {
    double calculateFare(double distance, RideType type);
}
