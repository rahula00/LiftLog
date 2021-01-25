package com.example.liftlog;

import android.util.Pair;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class User{
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference database = firebaseDatabase.getReference().child("Users");
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String UID = fAuth.getCurrentUser().getUid();
    public String email;
    public String name;
    public Calendar date;
    public boolean sex;
    public Pair<Float,Float> height;
    public float weight;


    public User(){
        //random values
        email = "email";
        name = "name";
        date = Calendar.getInstance();
        sex = true;
        height = new Pair<Float, Float>(0, 0);
        weight = 0;
    }

    void setEmail(String nEmail){
        email = nEmail;
        database.child(UID).child("Email").setValue(email);
    }

    void setName(String nName){
        name = nName;
        database.child(UID).child("Name").setValue(name);
    }

    void setDate(Integer year, Integer month, Integer day){
        date.set(year, month, day);
        database.child(UID).child("Date").setValue(date);
    }

    void setSex(String nSex){
        sex = nSex.equalsIgnoreCase("Male");
        database.child(UID).child("Sex").setValue(sex);
    }

    void setHeight(Pair<Float, Float> nHeight){
        height = nHeight;
        database.child(UID).child("Height").setValue(height);
    }

    void setWeight(float nWeight) {
        weight = nWeight;
        database.child(UID).child("Weight").setValue(weight);
    }
}
