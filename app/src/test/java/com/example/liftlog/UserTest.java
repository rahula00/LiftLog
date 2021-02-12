package com.example.liftlog;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
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
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("PST"));
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
        System.out.println(myUser.email);
        assertEquals("456@gmail.com", myUser.email);
    }

    @Test
    void setName() {
        myUser.setName("New Name");
        System.out.println(myUser.name);
        assertEquals("New Name", myUser.name);
    }

    @Test
    void setDate() {
        myUser.setDate(2020,2,12);
        System.out.println(myUser.birthDate.get(Calendar.YEAR));
    }

    @Test
    void setSex() {
        myUser.setSex(false);
        System.out.println(myUser.sex);
        assertEquals(false, myUser.sex);
    }

    @Test
    void setHeight() {
        myUser.setHeight(6,10);
        System.out.println(myUser.height);
    }

    @Test
    void setWeight() {
        myUser.setWeight(180);
        System.out.println(myUser.weight);
    }

    @Test
    void setUser_max() {
        System.out.println(myUser.user_max);
        myUser.setUser_max(0,100); //0 because the initial id is 0 probably needs a fix
        System.out.println(myUser.user_max);
    }
}