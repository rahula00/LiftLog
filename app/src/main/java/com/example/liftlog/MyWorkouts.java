package com.example.liftlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyWorkouts extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);
        listView = findViewById(R.id.listView);
        User myUser = MyApplication.user;
        WorkoutAdapter viewAdapter = new WorkoutAdapter(this, R.layout.myworkoutlistelement, (ArrayList)myUser.user_workouts);
        listView.setAdapter(viewAdapter);
    }

    //Handle back buttonfunctionalities.
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    // Overrides transition(s).
    // Allows us to change animations later if we want to.
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    // stops flicker when changing pages (even without animation)
    public void onAnimationEnd(Animation animation) {
        animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
        animation.setDuration(1);
    }
}