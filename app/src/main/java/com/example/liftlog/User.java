package com.example.liftlog;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.core.util.Pair;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
<<<<<<< Updated upstream
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
=======
import java.util.LinkedList;
import java.util.List;

>>>>>>> Stashed changes
import java.util.regex.Pattern;

public class User{
    public String email;
    public Bitmap profile_pic;
    public String name;
    public Calendar birthDate;
    public boolean sex;
    public Pair<Integer,Integer> height;
    public float weight;
    public float routine_id;
    public HashMap<String, ArrayList<Pair<Calendar,Integer>>> user_max;
<<<<<<< Updated upstream
    public List<Workout> user_workout;

    public User(String nEmail, String nName, Calendar nBirthDate, boolean nSex, Integer feet, Integer inches, float nWeight, List<Workout> queueWorkout){
=======
    public LinkedList<Workout> user_workout;

    public User(String nEmail, String nName, Calendar nBirthDate, boolean nSex, Integer feet, Integer inches, float nWeight, LinkedList<Workout> queueWorkout){
>>>>>>> Stashed changes
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
        this.user_max = new HashMap<String, ArrayList<Pair<Calendar, Integer>>>();
        this.user_max.put("0",init_max_list); //0 because the initial id is 0 probably needs a fix
        this.user_workout = queueWorkout;
   }

    public User(String nEmail){
        //random values
        this.email = nEmail;
        this.name = "";
        this.birthDate = Calendar.getInstance();
        this.sex = true;
        this.height = new Pair<>(0, 0);
        this.weight = 0;
        ArrayList<Pair<Calendar,Integer>> init_max_list = new ArrayList<Pair<Calendar,Integer>>();
        Pair<Calendar, Integer> init_max = new Pair<Calendar,Integer> (Calendar.getInstance(), 0);
        init_max_list.add(init_max);
        this.user_max = new HashMap<String, ArrayList<Pair<Calendar, Integer>>>();
        //Creates max of "0" for each exercise in array

//        Todo: change this to wherever the new exercise list is
        ArrayList<Exercise> exerciseArray = (MyApplication.exerciseList);

        for(int aI = 0; aI < exerciseArray.size(); aI++) {
            Pair<Calendar, Integer> temp_max = new Pair<Calendar,Integer> (Calendar.getInstance(), 0);
            ArrayList<Pair<Calendar,Integer>> tempList = new ArrayList<Pair<Calendar,Integer>>();
            tempList.add(temp_max);
<<<<<<< Updated upstream
            String exId = ""+exerciseArray.get(aI).ID;
            this.user_max.put(exId,tempList);
        }

        this.user_workout = new ArrayList<>();
=======
            String exId = exerciseArray.get(aI).ID+"";
            this.user_max.put(exId,tempList);
        }

        this.user_workout = new LinkedList<Workout>();
>>>>>>> Stashed changes
        this.profile_pic = null;
    }

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
        Calendar cal = Calendar.getInstance();
        birthDate.set(year, month, day);
        //kind of a bad way to check the date. checks if the day they put in was less than the current date
        if(birthDate.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR) && birthDate.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
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
            height = new Pair<Integer,Integer>(feet,inches);
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
            Pair<Calendar, Integer> new_max = new Pair<Calendar,Integer> (Calendar.getInstance(), weight);
            ArrayList<Pair<Calendar,Integer>> current_max_list = this.user_max.get(id);
            current_max_list.add(new_max);
            this.user_max.put(id,current_max_list);
            return true;
        }
        return false;
    }

    void setProfile_pic(Bitmap newImage){
        this.profile_pic = newImage;
    }

    void updateToFireBase()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        Pair<Calendar, Integer> temp_max = new Pair<Calendar,Integer> (Calendar.getInstance(), 0);
        ArrayList<Pair<Calendar,Integer>> tempList = new ArrayList<Pair<Calendar,Integer>>();
        tempList.add(temp_max);
<<<<<<< Updated upstream
        int exId = 3;
=======
        int exId = 1;
>>>>>>> Stashed changes


        user_max.put(exId+"", tempList);
        user_max.put(exId+1+"", tempList);
        user_max.put(exId+2+"", tempList);
        List<ArrayList<Pair<Calendar,Integer>>> list = new ArrayList<>(user_max.values());
        Log.i("UserTest", tempList.toString());
        Log.i("UserTest", user_max.toString());
        Log.i("UserTest", list.toString());

<<<<<<< Updated upstream
        Queue<ExerciseStats> esQueue = new LinkedList<ExerciseStats>();
=======
        LinkedList<ExerciseStats> esQueue = new LinkedList<ExerciseStats>();
>>>>>>> Stashed changes
        ExerciseStats myExStat = new ExerciseStats(1,0,0,0);
        esQueue.add(myExStat);
        Workout temp = new Workout(1,"test", "test", esQueue);
        user_workout.add(temp);
        dataRef.setValue(this);
        //dataRef.child("user_max").setValue(list);
        //dataRef.child("email").setValue(email);
        //dataRef.child("profile_pic").setValue(profile_pic);
        //dataRef.child("name").setValue(name);
        //dataRef.child("birthDate").setValue(birthDate);
        //dataRef.child("sex").setValue(sex);
        //dataRef.child("height").setValue(height);
        //dataRef.child("weight").setValue(weight);
        //dataRef.child("routine_id").setValue(routine_id);
        //dataRef.child("user_max").setValue(user_max);
        //dataRef.child("queue").setValue(user_workout);

        //public Hashtable<Integer, ArrayList<Pair<Calendar,Integer>>> user_max;
        //public Queue<Workout> user_workout;

<<<<<<< Updated upstream
        Log.d("User", "Pushed test + "+MyApplication.fUser.getUid());
=======
        //Log.d("User", "Pushed test + "+MyApplication.fUser.getUid());
>>>>>>> Stashed changes

    }
}
