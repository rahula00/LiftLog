package com.example.liftlog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Queue;
import java.util.regex.Pattern;

public class User{
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference database = firebaseDatabase.getReference().child("Users");
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String UID = fAuth.getCurrentUser().getUid();
    public String email;
    public Bitmap image;
    public String name;
    public Calendar birthDate;
    public boolean sex;
    public Pair<Integer,Integer> height;
    public float weight;
    public float routine_id;
    public Hashtable<Integer, Integer> user_max;
    public Queue<Workout> user_workout;

    public User(String nEmail, String nName, Calendar nBirthDate, boolean nSex, Integer feet, Integer inches, float nWeight, Queue<Workout> QueueWorkout){
        //random values
        this.email = nEmail;
        this.name = nName;
        this.birthDate = nBirthDate;
        this.sex = nSex;
        this.height = new Pair<Integer, Integer>(feet, inches);
        this.weight = nWeight;
        this.user_max = new Hashtable<Integer, Integer>(0,0);
        this.user_workout = QueueWorkout;
        try{
            this.image = BitmapFactory.decodeStream(MyApplication.getAppContext().getAssets().open("resource_default.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    void setHeight(Integer feet, Integer inches){
        //high doubts you are going to be less than 3 feet and working out
        if(feet > 3){
            height = new Pair<Integer,Integer>(feet,inches);
            database.child(UID).child("Height").setValue(height);
        }
    }

    void setWeight(float nWeight) {
        if(nWeight>0) {
            weight = nWeight;
            database.child(UID).child("Weight").setValue(weight);
        }
    }

    void setRoutine_id(Integer id){
        routine_id = id;
        database.child(UID).child("Routine_id").setValue(id);
    }

    void setUser_max(Integer id, Integer weight){
        user_max.put(id,weight);
        database.child(UID).child("User_max").setValue(user_max);
    }
}
