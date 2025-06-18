package org.anand.utils;

import org.anand.model.User;

import java.util.Objects;

// trending way : defensive Programming - Fail Fast
// usecase : banking application
public class DefensiveProgramming {

    public void processUser(User user) {
        // Fail fast with meaning-full message
        Objects.requireNonNull(user,"User cannot be null");
        Objects.requireNonNull(user.getName(),"Name cannot be null");

        // now we can safely process the logic
        processValiUser(user);

    }

    private static void processValiUser(User user) {
        System.out.println("processing logic");
    }

    private static void requireNonEmpty(String str,String message) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
