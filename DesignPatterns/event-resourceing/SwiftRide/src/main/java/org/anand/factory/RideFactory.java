package org.anand.factory;

import org.anand.dto.RideType;
import org.anand.dto.VehicleType;
import org.anand.entity.Location;
import org.anand.entity.Ride;
import org.anand.entity.Rider;
import org.anand.observer.ConsoleNotificationObserver;
import java.util.UUID;

// factory for ride
public class RideFactory {

    public static Ride createRide(Rider rider, Location pickup, Location dropoff,
                                  VehicleType vehicleType, RideType rideType) {
        String rideId = UUID.randomUUID().toString();
        Ride ride = new Ride(rideId, rider, pickup, dropoff, vehicleType, rideType);
        ride.addObserver(new ConsoleNotificationObserver());
        return ride;
    }
}
