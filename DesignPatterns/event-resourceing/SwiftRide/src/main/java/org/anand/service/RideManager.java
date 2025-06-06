package org.anand.service;

import lombok.Getter;
import lombok.Setter;
import org.anand.dto.RideStatus;
import org.anand.dto.RideType;
import org.anand.dto.VehicleType;
import org.anand.entity.Driver;
import org.anand.entity.Location;
import org.anand.entity.Ride;
import org.anand.entity.Rider;
import org.anand.factory.RideFactory;
import org.anand.fare.BaseFareCalculator;
import org.anand.fare.FareCalculator;
import org.anand.strategy.DriverMatchingStrategy;
import org.anand.strategy.NearestDriverStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ensure that only single instance get generated
@Setter
@Getter
public class RideManager {
    private static RideManager instance;
    private final List<Driver> drivers;
    private final List<Rider> riders;
    private final Map<String, Ride> rides;
    private DriverMatchingStrategy matchingStrategy;
    private FareCalculator fareCalculator;

    //make constructor private
    private RideManager() {
        this.drivers = new ArrayList<>();
        this.riders = new ArrayList<>();
        this.rides = new HashMap<>();
        this.matchingStrategy = new NearestDriverStrategy();
        this.fareCalculator = new BaseFareCalculator();
    }

    // provide public get() methode
    public static RideManager getInstance() {
        if (instance == null) {
            instance = new RideManager();
        }
        return instance;
    }

    // clone is not supported
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed for SecureObject");
    }

    public void addRider(Rider rider) {
        riders.add(rider);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    // setup ride
    public Ride requestRide(Rider rider, Location pickup, Location dropoff,
                            VehicleType vehicleType, RideType rideType) {
        Ride ride = RideFactory.createRide(rider, pickup, dropoff, vehicleType, rideType);
        Driver driver = matchingStrategy.findMatchingDriver(ride, drivers);
        if (driver != null) {
            ride.assignDriver(driver);
            rides.put(ride.getRideId(), ride);
            return ride;
        }
        ride.updateStatus(RideStatus.CANCELLED);
        return null;
    }

    // check progress of ride
    public void progressRide(String rideId) {
        Ride ride = rides.get(rideId);
        if (ride == null) {
            System.out.println("Ride " + rideId + " not found");
            return;
        }

        Map<RideStatus, RideStatus> statusTransitions = new HashMap<>();
        statusTransitions.put(RideStatus.CONFIRMED, RideStatus.DRIVER_EN_ROUTE);
        statusTransitions.put(RideStatus.DRIVER_EN_ROUTE, RideStatus.IN_PROGRESS);
        statusTransitions.put(RideStatus.IN_PROGRESS, RideStatus.COMPLETED);

        if (statusTransitions.containsKey(ride.getStatus())) {
            ride.updateStatus(statusTransitions.get(ride.getStatus()));
            if (ride.getStatus() == RideStatus.COMPLETED) {
                double distance = ride.getPickup().distanceTo(ride.getDropoff());
                ride.setFare(fareCalculator.calculateFare(distance, ride.getRideType()));
                ride.notify(String.format("Ride completed. Fare: $%.2f", ride.getFare()));
            }
        }
    }
}
