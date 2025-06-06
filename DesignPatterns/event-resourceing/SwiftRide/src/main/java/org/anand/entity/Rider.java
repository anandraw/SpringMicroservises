package org.anand.entity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Rider extends User{
    private final Location defaultLocation;
    private final List<Ride> rides;

    public Rider(String userId, String name, String phone, Location defaultLocation) {
        super(userId, name, phone);
        this.defaultLocation = defaultLocation;
        this.rides = new ArrayList<>();
    }
}
