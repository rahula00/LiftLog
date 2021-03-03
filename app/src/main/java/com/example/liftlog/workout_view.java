package com.example.liftlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class workout_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);

        Workout testW = new Workout(1, "myWorkout", "get big or die trying");
        ExerciseStats bench = new ExerciseStats(0, 150, 5, 1);
        ExerciseStats squat = new ExerciseStats(1, 225, 5, 1);
        ExerciseStats deadlift = new ExerciseStats(2, 300, 5, 1);
        ExerciseStats overheadpress = new ExerciseStats(3, 100, 5, 1);



    }
}