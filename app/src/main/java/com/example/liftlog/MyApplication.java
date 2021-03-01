package com.example.liftlog;

import android.app.Application;
import android.content.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;


public class MyApplication extends Application {

    private static Application thisApp;
    public static FirebaseAuth fAuth;
    public static FirebaseUser fUser;
    public static FirebaseDatabase dataRef;
    public static User user;
    public static ArrayList<Routine> routineList;
    public static ArrayList<Exercise> exerciseList;

    public static Application getApplication() {
        return thisApp;
    }

    public static Context getContext() {
        return thisApp.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        thisApp = this;
        this.exerciseList = new ArrayList<Exercise>() {
            {
                add(new Exercise(0, "Bench Press", ""));
                add(new Exercise(0, "Close Grip Bench Press", ""));
                add(new Exercise(0, "Deadlift", ""));
                add(new Exercise(0, "Front Squat",""));
                add(new Exercise(0, "Overhead Press",""));
                add(new Exercise(0, "Squat",""));
                add(new Exercise(0, "Sumo Deadlift",""));
            }
        };
        this.routineList = new ArrayList<Routine>() {
            {
                add(new NSuns());
            }
        };
    }
}

