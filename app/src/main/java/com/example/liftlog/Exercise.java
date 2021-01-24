package com.example.liftlog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class Exercise {
    //id & instructions
    public int ID;
    public String instructions;
    // for taking the specific image for the exercise and setting it as an ImageView
    public Context context;
    public Bitmap image;
    public ImageView description;

    public Exercise(int id, String directions, String image_path){
        ID = id;
        instructions = directions;
        image = BitmapFactory.decodeStream(context.getAssets().open(image_path));
        description.setImageBitmap(image);
    }
}
