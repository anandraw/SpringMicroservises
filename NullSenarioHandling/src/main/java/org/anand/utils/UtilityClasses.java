package org.anand.utils;

import org.anand.model.User;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

// first way handling null : Utility Classes(StringUtils,ObjectUtils,CollectionUtils)
public class UtilityClasses {

    public void processUser(String name, List<String> emails, User user) {

        // using StringUtils
        if (!StringUtils.isEmpty(name)) {
            System.out.println("Logic Processing :" + name);
        }

        // using CollectionUtils
        if (!CollectionUtils.isEmpty(emails)) {
            emails.stream()
                    .filter(email -> !StringUtils.isEmpty(email))
                    .forEach(email -> {
                        System.out.println("Logic Processing :" + email);
                    });
        }
        // ObjectUtils
        if (ObjectUtils.isEmpty(user)) {
            System.out.println("city :" + user.getAddress().getCity());
        }
    }
}
