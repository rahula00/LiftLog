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
    public Calendar birthDate;
    public boolean sex;
    public Pair<Integer,Integer> height;
    public float weight;


    public User(){
        //random values
        email = "email";
        name = "name";
        birthDate = Calendar.getInstance();
        sex = true;
        height = new Pair<Integer, Integer>(0, 0);
        weight = 0;
    }

    void setEmail(String nEmail){
        //add checking for this
        email = nEmail;
        database.child(UID).child("Email").setValue(email);
    }

    void setName(String nName){
        name = nName;
        database.child(UID).child("Name").setValue(name);
    }

    void setDate(Integer year, Integer month, Integer day){
        //need to add checking for this
        birthDate.set(year, month, day);
        database.child(UID).child("Date").setValue(birthDate);
    }

    void setSex(boolean nSex){
        sex = nSex;
        database.child(UID).child("Sex").setValue(sex);
    }

    void setHeight(Pair<Integer, Integer> nHeight){
        height = nHeight;
        database.child(UID).child("Height").setValue(height);
    }

    void setWeight(float nWeight) {
        if(nWeight<0){
            //some std err
            //exit
        }
        weight = nWeight;
        database.child(UID).child("Weight").setValue(weight);
    }
}
