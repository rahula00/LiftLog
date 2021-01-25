package com.example.liftlog;

import android.util.Pair;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.regex.Pattern;

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
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if(p.matcher(nEmail).matches()){
            email = nEmail;
            database.child(UID).child("Email").setValue(email);
        }
    }

    void setName(String nName){
        name = nName;
        database.child(UID).child("Name").setValue(name);
    }

    void setDate(Integer year, Integer month, Integer day){
        Calendar cal = Calendar.getInstance();
        birthDate.set(year, month, day);
        //kind of a bad way to check the date. checks if the day they put in was less than the current date
        if(birthDate.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR) && birthDate.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
            database.child(UID).child("Date").setValue(birthDate);
        }
    }

    void setSex(boolean nSex){
        sex = nSex;
        database.child(UID).child("Sex").setValue(sex);
    }

    void setHeight(Pair<Integer, Integer> nHeight){
        Integer foot = nHeight.first;
        Integer inches = nHeight.second;
        //high doubts you are going to be less than 3 feet and working out
        if(foot > 3){
            height = nHeight;
            database.child(UID).child("Height").setValue(height);
        }
    }

    void setWeight(float nWeight) {
        if(nWeight>0) {
            weight = nWeight;
            database.child(UID).child("Weight").setValue(weight);
        }
    }
}
