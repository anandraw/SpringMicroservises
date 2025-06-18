package org.anand.service;

import org.anand.model.User;

import java.util.List;

public class UserService {

    public void processUser(String name, List<String> emails, User user) {

        // traditional way to handling null
        if (name != null && !name.isEmpty()) {
            System.out.println("Logic Processing :" + name);
        }

        if (emails != null && !emails.isEmpty()) {
            emails.forEach(email -> {
                if (email != null && !email.isEmpty()) {
                    System.out.println("send email:");
                }
            });
        }
        if (user != null && user.getAddress() != null && user.getAddress().getCity() != null) {
            System.out.println("city :" + user.getAddress().getCity());
        }
    }
}
