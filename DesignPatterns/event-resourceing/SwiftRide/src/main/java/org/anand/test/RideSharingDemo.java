package org.anand.test;

import org.anand.dto.RideType;
import org.anand.dto.VehicleType;
import org.anand.entity.Driver;
import org.anand.entity.Location;
import org.anand.entity.Ride;
import org.anand.entity.Rider;
import org.anand.fare.FareCalculator;
import org.anand.fare.SurgeFareDecorator;
import org.anand.service.RideManager;
import org.anand.strategy.NearestDriverStrategy;

import java.util.concurrent.atomic.AtomicReference;

public class RideSharingDemo {
    public static void main(String[] args) {
        RideManager rideManager = RideManager.getInstance();

        // Create riders and drivers
        Rider rider1 = new Rider("R1", "Alice", "1234567890", new Location(0, 0));
        Driver driver1 = new Driver("D1", "Bob", "0987654321", VehicleType.AUTO_RICKSHAW, new Location(1, 1));
        Driver driver2 = new Driver("D2", "Charlie", "1122334455", VehicleType.AUTO_RICKSHAW, new Location(2, 2));


        rideManager.addRider(rider1);
        rideManager.addDriver(driver1);
        rideManager.addDriver(driver2);

        // Demo nearest driver strategy
        System.out.println("Using Nearest Driver Strategy:");
        Ride ride = rideManager.requestRide(rider1, new Location(0, 0), new Location(5, 5),
                VehicleType.AUTO_RICKSHAW, RideType.NORMAL);
        if (ride != null) {
            rideManager.progressRide(ride.getRideId());
            rideManager.progressRide(ride.getRideId());
            rideManager.progressRide(ride.getRideId());
        }

        // Demo best rated driver strategy
        System.out.println("\nUsing Best Rated Driver Strategy:");
        rideManager.setMatchingStrategy(new NearestDriverStrategy());
        ride = rideManager.requestRide(rider1, new Location(0, 0), new Location(5, 5),
                VehicleType.Car, RideType.CARPOOL);
        if (ride != null) {
            // Apply surge pricing
            rideManager.setFareCalculator(new SurgeFareDecorator(rideManager.getFareCalculator()));
            rideManager.progressRide(ride.getRideId());
            rideManager.progressRide(ride.getRideId());
            rideManager.progressRide(ride.getRideId());
        }
    }

    // Helper method to get fare calculator (used in RideManager)
    private FareCalculator getFareCalculator() {
        AtomicReference<FareCalculator> calculator = new AtomicReference<>();
        // This would need proper implementation based on your RideManager structure
        return calculator.get();
    }
}
