package com.example.liftlog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Queue;
import java.util.regex.Pattern;

public class User extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference database = firebaseDatabase.getReference().child("Users");
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String UID = fAuth.getCurrentUser().getUid();
    public String email;
    public Bitmap profile_pic;
    public String name;
    public Calendar birthDate;
    public boolean sex;
    public Pair<Integer,Integer> height;
    public float weight;
    public float routine_id;
    public Hashtable<Integer, ArrayList<Pair<Calendar,Integer>>> user_max;
    public Queue<Workout> user_workout;

    public User(String nEmail, String nName, Calendar nBirthDate, boolean nSex, Integer feet, Integer inches, float nWeight, Queue<Workout> queueWorkout){
        //random values
        this.email = nEmail;
        this.name = nName;
        this.birthDate = nBirthDate;
        this.sex = nSex;
        this.height = new Pair<>(feet, inches);
        this.weight = nWeight;
        ArrayList<Pair<Calendar,Integer>> init_max_list = new ArrayList<Pair<Calendar,Integer>>();
        Pair<Calendar, Integer> init_max = new Pair<Calendar,Integer> (Calendar.getInstance(), 0);
        init_max_list.add(init_max);
        this.user_max = new Hashtable<Integer, ArrayList<Pair<Calendar, Integer>>>();
        this.user_max.put(0,init_max_list); //0 because the initial id is 0 probably needs a fix
        this.user_workout = queueWorkout;
        this.profile_pic = BitmapFactory.decodeResource(getResources(), R.drawable.resource_default);
   }

    boolean setEmail(String nEmail){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if(p.matcher(nEmail).matches()){
            email = nEmail;
            database.child(UID).child("Email").setValue(email);
            return true;
        }
        return false;
    }

    void setName(String nName){
        name = nName;
        database.child(UID).child("Name").setValue(name);
    }

    boolean setDate(Integer year, Integer month, Integer day){
        Calendar cal = Calendar.getInstance();
        birthDate.set(year, month, day);
        //kind of a bad way to check the date. checks if the day they put in was less than the current date
        if(birthDate.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR) && birthDate.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
            database.child(UID).child("Date").setValue(birthDate);
            return true;
        }
        return false;
    }

    void setSex(boolean nSex){
        sex = nSex;
        database.child(UID).child("Sex").setValue(sex);
    }

    boolean setHeight(Integer feet, Integer inches){
        //high doubts you are going to be less than 3 feet and working out
        if(feet > 3){
            height = new Pair<Integer,Integer>(feet,inches);
            database.child(UID).child("Height").setValue(height);
            return true;
        }
        return false;
    }

    boolean setWeight(float nWeight) {
        if(nWeight>0) {
            weight = nWeight;
            database.child(UID).child("Weight").setValue(weight);
            return true;
        }
        return false;
    }

    void setRoutine_id(Integer id){
        routine_id = id;
        database.child(UID).child("Routine_id").setValue(id);
    }

    boolean setUser_max(Integer id, Integer weight){
        if(weight>0){
            Pair<Calendar, Integer> new_max = new Pair<Calendar,Integer> (Calendar.getInstance(), weight);
            ArrayList<Pair<Calendar,Integer>> current_max_list = this.user_max.get(id);
            current_max_list.add(new_max);
            this.user_max.put(id,current_max_list);
            database.child(UID).child("User_max").setValue(user_max);
            return true;
        }
        return false;
    }
}
