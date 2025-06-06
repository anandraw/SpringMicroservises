package org.anand.utils;


import org.anand.model.Address;
import org.anand.model.User;

import java.util.Optional;

// Optional: The modern functional approach
public class UsingOptional {

    public Optional<User> findByUserId(int userId) {
        User user = new User();
        return Optional.ofNullable(user);
    }

    // solving deep null problem
    // old way : Pyramid Of doom
    public String getUserCity(User user) {
        if (user != null){
            if (user.getAddress()!=null){
                if (user.getAddress().getCity()!=null){
                    return user.getAddress().getCity();
                }
            }
        }
        return "not found";
    }

    // new way : clean and safe
    public String getUserCityClean(User user) {
       return Optional.ofNullable(user)
               .map(User::getAddress)
               .map(Address::getCity)
               .orElse("not found");
    }
}
