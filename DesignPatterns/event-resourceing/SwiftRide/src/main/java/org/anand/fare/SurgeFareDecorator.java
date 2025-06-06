package org.anand.fare;

import lombok.AllArgsConstructor;
import org.anand.dto.RideType;

@AllArgsConstructor
public class SurgeFareDecorator implements FareCalculator{

    private final FareCalculator wrapped;
    private final double surgeMultiplier;

    public SurgeFareDecorator(FareCalculator wrapped) {
        this(wrapped, 1.5);
    }

    @Override
    public double calculateFare(double distance, RideType rideType) {
        return wrapped.calculateFare(distance, rideType) * surgeMultiplier;
    }
}
