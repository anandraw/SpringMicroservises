package org.anand.strategy;

import lombok.extern.slf4j.Slf4j;
import org.anand.entity.Driver;
import org.anand.entity.Ride;
import java.util.List;

@Slf4j
public class NearestDriverStrategy implements DriverMatchingStrategy{

    @Override
    public Driver findMatchingDriver(Ride ride, List<Driver> drivers) {

        double minDistance = Double.MAX_VALUE;
        log.info("min-Distance:"+minDistance);

        // choose the best driver based on distance
        Driver nearestdriver = null;

        for (Driver driver : drivers) {
           // first check the availability of driver and vehicle is present or not
            if (driver.isAvailable() && driver.getVehicleType()==ride.getVehicleType()) {
                log.info("driver found");
                double distance = ride.getPickup().distanceTo(driver.getCurrentLocation());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestdriver = driver;
                }
            }
        }
        return nearestdriver;
    }
}
