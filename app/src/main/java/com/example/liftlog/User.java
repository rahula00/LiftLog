package com.example.liftlog;

import java.util.Calendar;
import java.util.Date;

public class User{
    public String email;
    public String name;
    public Calendar date;
    public boolean sex;
    public float height;
    public float weight;

    public User(){
        //random values
        email = "email";
        name = "name";
        date = Calendar.getInstance();
        sex = true;
        height = 6;
        weight = 180;
    }
}
