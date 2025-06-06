package org.anand.fare;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anand.dto.RideType;

@AllArgsConstructor
@Slf4j
public class BaseFareCalculator implements FareCalculator {
     private final double baseFare;
     private final double perKmRate;

    public BaseFareCalculator() {
        this(5.0, 2.0);
    }
    @Override
    public double calculateFare(double distance, RideType type) {
        log.info("BaseFareCalculator calculateFare");
        double fare =baseFare + (distance * perKmRate);
        return fare;
    }
}
