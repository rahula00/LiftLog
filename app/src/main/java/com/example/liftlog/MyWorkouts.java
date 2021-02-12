package com.example.liftlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;

import java.util.ArrayList;

public class MyWorkouts extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);

        listView = findViewById(R.id.listView);
        ArrayList<Exercise> arrayList = new ArrayList<>();
        arrayList.add(new Exercise(1,"BenchPress", "Hold the bar"));
        arrayList.add(new Exercise(2,"Curls", "Curl the thing" ));
        arrayList.add(new Exercise(3,"LegPress", "Push with legs"));
        arrayList.add(new Exercise(4,"BenchPress", "Hold the bar"));
        arrayList.add(new Exercise(5,"Curls", "Curl the thing" ));
        arrayList.add(new Exercise(6,"LegPress", "Push with legs"));
        arrayList.add(new Exercise(7,"Incline", "Hold the bar"));
        WorkoutExerciseAdapter adapter = new WorkoutExerciseAdapter(this, R.layout.myworkoutlistelement, arrayList);
        listView.setAdapter(adapter);
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