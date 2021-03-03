package com.example.liftlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class Routines extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        listView = findViewById(R.id.routineListView);
        RoutinesAdapter viewAdapter = new RoutinesAdapter(this, R.layout.routineslistelement, MyApplication.routineList);
        listView.setAdapter(viewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getApplicationContext(), RoutineView.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }


    //Handle back button functionalities.
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