package com.example.liftlog;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static Context context;
    FirebaseAuth fAuth;
    FirebaseUser fUser = fAuth.getCurrentUser();
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
