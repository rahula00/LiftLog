package com.example.liftlog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.core.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Exercise {
    // exercise id, name, & instructions
    public int ID;
    public String name;
    public String instructions;
    // for taking the specific image for the exercise and setting it as an ImageView
    public Bitmap image;
//    public ImageView description;


    public Exercise(int id, String exerciseName, String directions, Bitmap exImage) {
        ID = id;
        name = exerciseName;
        instructions = directions;
        image = exImage;
    }

    public Exercise(int id, String exerciseName, String directions) {
        ID = id;
        name = exerciseName;
        instructions = directions;
        image = null;
    }


}
