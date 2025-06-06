package org.anand.model;

import lombok.Data;

@Data
public class User {

    private String name;
    private String email;
    private Address address;

    public String getName() {
        return "Guest";
    }
    public String getEmail() {
        return "Guest@gmail.com";
    }
    public boolean isActivated() {
        return false;
    }

    public void sendNotification(String message) {

    }

}
