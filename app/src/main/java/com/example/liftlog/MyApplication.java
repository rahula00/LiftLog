package com.example.liftlog;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static Context context;
    public static FirebaseAuth fAuth;
    public static FirebaseUser fUser;
    public static DatabaseReference dataRef;
    public static User user;
    public static ArrayList<Exercise> exerciseList;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return MyApplication.context;
    }
}
