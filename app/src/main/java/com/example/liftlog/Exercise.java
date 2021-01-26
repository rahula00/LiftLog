package com.example.liftlog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;

public class Exercise {
    // exercise id, name, & instructions
    public int ID;
    public String name;
    public String instructions;
    // for taking the specific image for the exercise and setting it as an ImageView
    public static Context context;
    public Bitmap image;
    public ImageView description;

    public Exercise(int id, String exerciseName, String directions, String assetName) {
        ID = id;
        name = exerciseName;
        instructions = directions;
        // using open() requires this try/catch statement but probably won't throw an error
        try {
            image = BitmapFactory.decodeStream(context.getAssets().open(assetName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        description.setImageBitmap(image);
    }
}
