package com.example.liftlog;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class RoutineView extends AppCompatActivity {
    FirebaseAuth fAuth;
    Button mStartBtn;
    User myUser;
    Routine routine;
    TextView mName, mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        fAuth = FirebaseAuth.getInstance();
        mStartBtn = findViewById(R.id.startButton);

        mName = findViewById(R.id.routineName);
        mDescription = findViewById(R.id.routineDescription);

        String nameText = routine.name;
        String DescriptionText = routine.description;

        mName.setText(nameText);
        mDescription.setText(DescriptionText);

        mDescription.setMovementMethod(new ScrollingMovementMethod());
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                myUser.setRoutine(routine.id, routine.workouts);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //This Should go to myRoutines page instead of MainActivity
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
