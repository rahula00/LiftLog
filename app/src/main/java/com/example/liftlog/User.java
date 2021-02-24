package com.example.liftlog;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.core.util.Pair;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.regex.Pattern;

public class User{
    public String email;
    public Bitmap profile_pic;
    public String name;
    public GregorianCalendar birthDate;
    public boolean sex;
    public Pair<Integer,Integer> height;
    public float weight;
    public int routine_id;
    public HashMap<String, Integer> user_max;
    public LinkedList<Workout> user_workout;

    public User(String nEmail, String nName, GregorianCalendar nBirthDate, boolean nSex, Integer feet, Integer inches, float nWeight, LinkedList<Workout> queueWorkout){
        //random values
        this.email = nEmail;
        this.name = nName;
        this.birthDate = nBirthDate;
        this.sex = nSex;
        this.height = new Pair<>(feet, inches);
        this.weight = nWeight;
        this.user_max = new HashMap<>();
        user_max.put("0_k",0); //0 because the initial id is 0 probably needs a fix
        this.user_workout = queueWorkout;

   }

    public User(String nEmail){
        //random values
        this.email = nEmail;
        this.name = "";
        this.birthDate = new GregorianCalendar();
        this.sex = true;
        this.height = new Pair<Integer, Integer>(0, 0);
        this.weight = 0;
        this.user_max = new HashMap<String, Integer>();
        user_max.put("0_k",0);

        this.user_workout = new LinkedList<>();
        ExerciseStats tempEx = new ExerciseStats(0,0,0,0);
        LinkedList<ExerciseStats> tempList2 = new LinkedList<>();
        tempList2.add(tempEx);
        user_workout.add(new Workout(0, "0","0",  tempList2));

        this.profile_pic = null;
    }

    public User(DataSnapshot dataObj)
    {
        height = new Pair<>(0, 0);
        this.email = (String) dataObj.child("email").getValue();
        this.name = (String) dataObj.child("name").getValue();
        this.sex = (boolean) dataObj.child("sex").getValue();
        long H1 = (long) dataObj.child("height").child("first").getValue();
        long H2 = (long) dataObj.child("height").child("second").getValue();
        this.height = new Pair<>((int) H1, (int) H2);
        long W1 = (long) dataObj.child("weight").getValue();
        this.weight = (float) W1;
        long R1 = (long)dataObj.child("routine_id").getValue();
        this.routine_id = (int) R1;

        this.user_workout = new LinkedList<>();
        for(DataSnapshot workSnap : dataObj.child("user_workout").getChildren())
        {
            Workout newWork = new Workout((Long) workSnap.child("id").getValue(), (String) workSnap.child("description").getValue(), (String) workSnap.child("name").getValue());
            if(workSnap.child("statsList")!= null) {
                ArrayList<ExerciseStats> tempArrayList = new ArrayList<>();
                for(DataSnapshot exerSnap : workSnap.child("statsList").getChildren())
                {
                    long id = (long) exerSnap.child("exercise").getValue();
                    long weightassigned = (long) exerSnap.child("weight").getValue();
                    long numreps = (long) exerSnap.child("reps").getValue();
                    long numsets = (long) exerSnap.child("sets").getValue();
                    boolean trigger = (boolean) exerSnap.child("trigger_max_change").getValue();
                    ExerciseStats newExer = new ExerciseStats((int) id, (int) weightassigned, (int) numreps, (int) numsets, trigger);
                    tempArrayList.add(newExer);
                }
                newWork.statsList = new LinkedList<>(tempArrayList);
            }
            user_workout.add(newWork);
        }
        long T1 = (long) dataObj.child("birthDate").child("timeInMillis").getValue();
        this.birthDate = new GregorianCalendar();
        this.birthDate.setTimeInMillis(T1);
        this.user_max = (HashMap<String,Integer>) dataObj.child("user_max").getValue();
    }

    public User(){}

    boolean setEmail(String nEmail){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if(p.matcher(nEmail).matches()){
            email = nEmail;
            return true;
        }
        return false;
    }

    void setName(String nName){
        name = nName;
    }

    boolean setDate(Integer year, Integer month, Integer day){
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(year, month, day);
        //kind of a bad way to check the date. checks if the day they put in was less than the current date
        if(birthDate.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR) && birthDate.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
            birthDate = cal;
            return true;
        }
        return false;
    }

    void setSex(boolean nSex){
        sex = nSex;
    }

    boolean setHeight(Integer feet, Integer inches){
        //high doubts you are going to be less than 3 feet and working out
        if(feet > 3){
            height = new Pair<>(feet, inches);
            return true;
        }
        return false;
    }

    boolean setWeight(float nWeight) {
        if(nWeight>0) {
            weight = nWeight;
            return true;
        }
        return false;
    }

    void setRoutine_id(Integer id){
        routine_id = id;
    }

    boolean setUser_max(String id, Integer weight){
        if(weight>0){
            this.user_max.put(id,weight);
            return true;
        }
        return false;
    }

    void setProfile_pic(Bitmap newImage){
        this.profile_pic = newImage;
    }

    void updateToFirebase()
    {
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference uploadRef = FirebaseDatabase.getInstance().getReference("Users").child(UID);
        uploadRef.setValue(this);
    }


    void printOut()
    {
        String printMe = "\nemail:" +email+"\nname:" +name+"\nbirthDate:" +birthDate+"\nsex:" +sex+"\nheight:" +height+
                "\nweight:" +weight+"\nroutine:" +routine_id+"\nuser_max:" +user_max+"\nuser_workout:" +user_workout;
        Log.i("printOut", printMe);
    }
}
