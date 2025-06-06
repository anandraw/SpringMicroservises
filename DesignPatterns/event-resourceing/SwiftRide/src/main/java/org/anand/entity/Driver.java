package org.anand.entity;

import lombok.Getter;
import org.anand.dto.VehicleType;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Driver extends User{

    private final VehicleType vehicleType;
    private Location currentLocation;
    private boolean isAvailable;
    private final List<Ride> rides;

    public Driver(String userId, String name, String phone, VehicleType vehicleType, Location currentLocation) {
        super(userId, name, phone);
        this.vehicleType = vehicleType;
        this.currentLocation = currentLocation;
        this.isAvailable = true;
        //this.rating = 5.0;
        this.rides = new ArrayList<>();
    }
}
