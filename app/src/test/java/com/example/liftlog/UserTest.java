package com.example.liftlog;

import androidx.core.util.Pair;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    public Queue<ExerciseStats> setQueue(){
        ExerciseStats myExStat = new ExerciseStats(1,0,0,0);
        Queue<ExerciseStats> esQueue = new LinkedList<ExerciseStats>();
        esQueue.add(myExStat);
        return esQueue;
    }

    public Queue<Workout> setWorkout(Queue<ExerciseStats> stats){
        Workout myWorkout = new Workout(1,"workoutName", "description", stats);
        Queue<Workout> workoutQueue = new LinkedList<Workout>();
        workoutQueue.add(myWorkout);
        return workoutQueue;
    }

    String email = "123@gmail.com";
    String name = "Name";
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
    boolean sex = true;
    Integer feet = 5;
    Integer inches = 10;
    float weight = 160;
    Queue<ExerciseStats> myStats = setQueue();
    Queue<Workout> myWorkout = setWorkout(myStats);
    User myUser = new User(email, name, cal, sex, feet, inches, weight, myWorkout);

    @Test
    void setEmail() {
        myUser.setEmail("456@gmail.com");
        assertEquals("456@gmail.com", myUser.email);
    }

    @Test
    void setName() {
        myUser.setName("New Name");
        assertEquals("New Name", myUser.name);
    }

    @Test
    void setDate() {
        Calendar cale = Calendar.getInstance(TimeZone.getTimeZone("PST"));
        myUser.setDate(cale.get(Calendar.YEAR),cale.get(Calendar.MONTH),cale.get(Calendar.DAY_OF_MONTH));
        assertEquals(myUser.birthDate.get(Calendar.YEAR), cale.get(Calendar.YEAR));
        assertEquals(myUser.birthDate.get(Calendar.MONTH), cale.get(Calendar.MONTH));
        assertEquals(myUser.birthDate.get(Calendar.DAY_OF_MONTH), cale.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    void setSex() {
        myUser.setSex(false);
        assertEquals(false, myUser.sex);
    }

    @Test
    void setHeight() {
        myUser.setHeight(6,10);
        Pair<Integer, Integer> newHeight = new Pair<Integer, Integer> (6,10);
        assertEquals(myUser.height, newHeight);
    }

    @Test
    void setWeight() {
        myUser.setWeight(180);
        float newHeight = 180;
        assertEquals(myUser.weight, newHeight);
    }
}