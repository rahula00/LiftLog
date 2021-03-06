package com.example.liftlog;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MyWorkouts extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);

        User myUser = MyApplication.user;
        for(int i = 0; i< myUser.user_workouts.size(); i++){
            myUser.user_workouts.get(i).id = i;
        }

        listView = findViewById(R.id.listView);
        WorkoutAdapter viewAdapter = new WorkoutAdapter(this, R.layout.myworkoutlistelement, (ArrayList)myUser.user_workouts);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Workout selected = (Workout) listView.getItemAtPosition(position);
                Intent intent = new Intent(MyApplication.getContext(), workout_view.class);
                intent.putExtra("WORKOUT_ID", selected.id);
                startActivity(intent);
                finish();
            }
        });

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