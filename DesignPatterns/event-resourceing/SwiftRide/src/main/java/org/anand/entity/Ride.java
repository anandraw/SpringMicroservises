package org.anand.entity;

import lombok.Getter;
import lombok.Setter;
import org.anand.dto.RideStatus;
import org.anand.dto.RideType;
import org.anand.dto.VehicleType;
import org.anand.observer.ConsoleNotificationObserver;
import org.anand.observer.NotificationObserver;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Ride {

    private final String rideId;
    private final Rider rider;
    private Driver driver;
    private final Location pickup;
    private final Location dropoff;
    private final VehicleType vehicleType;
    private final RideType rideType;
    private RideStatus status;
    private Double fare;
    private final List<ConsoleNotificationObserver> observers;

    public Ride(String rideId, Rider rider, Location pickup, Location dropoff,
                VehicleType vehicleType, RideType rideType) {
        this.rideId = rideId;
        this.rider = rider;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.vehicleType = vehicleType;
        this.rideType = rideType;
        this.status = RideStatus.REQUESTED;
        this.observers = new ArrayList<>();
    }
    public void addObserver(ConsoleNotificationObserver observer) {
        observers.add(observer);
    }

    public void notify(String message) {
        for (ConsoleNotificationObserver observer : observers) {
            observer.notify(message, rideId);
        }
    }
    public void assignDriver(Driver driver) {
        this.driver = driver;
        this.status = RideStatus.CONFIRMED;
        notify("Driver " + driver.getName() + " assigned to ride");
        driver.setAvailable(false);
        driver.getRides().add(this);
        rider.getRides().add(this);
    }

    public void updateStatus(RideStatus newStatus) {
        this.status = newStatus;
        notify("Ride status updated to " + newStatus.getValue());
        if (newStatus == RideStatus.COMPLETED && driver != null) {
            driver.setAvailable(true);
        }
    }
}
