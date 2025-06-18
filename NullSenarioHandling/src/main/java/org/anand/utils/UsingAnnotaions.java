package org.anand.utils;

import org.anand.model.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

// use annotaion like @Nonnull @Nullable
public class UsingAnnotaions {

    public void processUserData(@NonNull String name,@Nullable String email,
                                @Nullable List<String> permissions)
    {
        System.out.println("name: " + name + ", email: " + email + ", permissions: " + permissions);

        if (email!=null){ // IDE reminds you to check values
            System.out.println("email: " + email);
        }
        permissions.forEach(System.out::println);
    }

    @Nullable
    public User findUserByEmail(String email){
        Objects.requireNonNull(email, "email cannot be null");
        User user = new User();
        return user;
    }
}
