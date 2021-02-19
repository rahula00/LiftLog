package com.example.liftlog;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.core.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

public class MyApplication extends Application {
    private static Context context;
    public static User user;
    public static ArrayList<Exercise> exerciseList;



    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        this.exerciseList = new ArrayList<Exercise>();
    }


    public static Context getAppContext(){
        return MyApplication.context;
    }

    public User returnUser(){
        return MyApplication.user;
    }


}
