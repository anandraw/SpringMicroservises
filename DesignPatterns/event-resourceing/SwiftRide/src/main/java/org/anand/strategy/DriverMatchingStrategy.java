package org.anand.strategy;

import org.anand.entity.Driver;
import org.anand.entity.Ride;

import java.util.List;

public interface DriverMatchingStrategy {
    Driver findMatchingDriver(Ride ride, List<Driver> drivers);
}
