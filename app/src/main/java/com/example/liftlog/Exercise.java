package com.example.liftlog;

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
    public Bitmap image;
    public ImageView description;

    public Exercise(int id, String exerciseName, String directions, String assetName) {
//        MyApplication app = ((MyApplication)getApplication());
        ID = id;
        name = exerciseName;
        instructions = directions;
        // using open() requires this try/catch statement but probably won't throw an error
        try {
            image = BitmapFactory.decodeStream(MyApplication.getContext().getAssets().open(assetName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        description.setImageBitmap(image);
    }
}
