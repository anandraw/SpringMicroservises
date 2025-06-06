package org.anand.utils;

import org.anand.model.User;

public class NullObjectPattern extends User {

    public static final NullObjectPattern instance = new NullObjectPattern();
    private NullObjectPattern() {

    }
    @Override
    public String getName(){
        return "Guest";
    }
    @Override
    public String getEmail(){
        return "guest@gmail.com";
    }

}

