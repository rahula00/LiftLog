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
    User myUser = new User("email", "name", Calendar.getInstance(), true, 0, 0, 0, null);
    Routine routine = new Routine(0,"nSuns","nSuns 531 is an beginner/intermediate program focused on multiple sets of main lifts. It takes advantage of the compound engagement of main lifts and slow, small increases in overall weight. Accessory lifts should be used to target muscles that aren't included in main lifts. The ones suggested can be switched out for others, depending on the goals of the user.\n" +
            "\n" +
            "In the tables below, sets that are bolded are \"max\" sets. These represent 90% of the maximum weight you can lift unless indicated otherwise. The sets prior to these should be warmed up into with lower weight while the sets following should decrease in weight. Sets that are italicized are to be done with the same weight, around 60% of the maximum weight capable or less.", "x");
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
