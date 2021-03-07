package com.example.liftlog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class workout_view extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User myUser = MyApplication.user;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_view);

        LinearLayout scroll = (LinearLayout) findViewById(R.id.linearInScroll);
        ArrayList<Integer> checkList = new ArrayList<Integer>();

        Integer arrayID = (int) getIntent().getLongExtra("WORKOUT_ID", 0);
        LinkedList<ExerciseStats> exerciseArray = myUser.user_workouts.get(arrayID).statsList;

        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            public void onClick(View view) {

                int viewID = (int) view.getTag();
                View parentView = (View) view.getParent();
                TextView triggerTagView = (TextView) parentView.findViewById(R.id.exerciseName);
                boolean trigger = (boolean) triggerTagView.getTag();

                if (trigger) {
                    View v = scroll.getChildAt(0);
                    TextView tempReps = (TextView) v.findViewById(R.id.exerciseReps);
                    String maxRepsString = tempReps.getText().toString().trim();
                    scroll.removeView(v);
                    //TODO: direct towards popup
                    if (!TextUtils.isEmpty(maxRepsString)) {
                        int maxReps = Integer.parseInt(maxRepsString);
                        String exKey = String.valueOf(exerciseArray.get(viewID).exercise);
                        myUser.user_max.put((exKey + "_k"), maxReps);
                    }
                    exerciseArray.pop();
                    if (scroll.getChildCount() > 0) {
                        View v2 = scroll.getChildAt(0);
                        Button tempButton = (Button) v2.findViewById(R.id.btnDone);
                        tempButton.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (!checkList.contains(viewID)) {
                        checkList.add(viewID);
                        view.setBackgroundTintList(MyApplication.getContext().getResources().getColorStateList(R.color.red));
                        Button b = (Button) view;
                        b.setText("Confirm");
                        b.setTextSize(10);
                    } else {
                        View v = scroll.getChildAt(0);
                        scroll.removeView(v);
                        exerciseArray.pop();
                        if (scroll.getChildCount() > 0) {
                            View v2 = scroll.getChildAt(0);
                            Button tempButton = (Button) v2.findViewById(R.id.btnDone);
                            tempButton.setVisibility(View.VISIBLE);
                        } else {
                            Workout toRemove = myUser.user_workouts.get(arrayID);
                            myUser.user_workouts.remove(toRemove);
                            if (myUser.user_workouts.size() == 0) {
                                Toast.makeText(workout_view.this, "Week is complete!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MyApplication.getContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(MyApplication.getContext(), MyWorkouts.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        };


        LayoutInflater inflater = getLayoutInflater();


        for(int i = 0; i < exerciseArray.size(); i++){
            ExerciseStats currentExercise = exerciseArray.get(i);
            int exEx = currentExercise.exercise;
            int exID = i;
            int exWeight = currentExercise.weight;
            int exReps = currentExercise.reps;
            int exSets = currentExercise.sets;
            boolean exTrigger = currentExercise.trigger_max_change;
            Bitmap exImage = MyApplication.exerciseList.get(exEx).image;

            ConstraintLayout newLayout = (ConstraintLayout) inflater.inflate(R.layout.workout_template, scroll,false);
            newLayout.setTag(exID);

            TextView exerciseName = (TextView) newLayout.findViewById(R.id.exerciseName);
            String exerciseNameTemp = MyApplication.exerciseList.get(exEx).name;
            exerciseName.setText(exerciseNameTemp);
            exerciseName.setTag(exTrigger);

            TextView exerciseWeight = (TextView) newLayout.findViewById(R.id.exerciseWeight);
            exerciseWeight.setText(String.valueOf(exWeight));
            Log.d("WEIGHT: ", String.valueOf(exWeight));

            TextView exerciseReps = (TextView) newLayout.findViewById(R.id.exerciseReps);
            if(!exTrigger) exerciseReps.setText(String.valueOf(exReps));

            TextView exerciseSets = (TextView) newLayout.findViewById(R.id.exerciseSets);
            exerciseSets.setText(String.valueOf(exSets));

            Button btnDone = (Button) newLayout.findViewById(R.id.btnDone);
            btnDone.setTag(exID);
            if(!exTrigger) btnDone.setOnClickListener(listener);

            ImageView exerciseImage = (ImageView) newLayout.findViewById(R.id.exerciseImage);
            exerciseImage.setImageBitmap(exImage);

            // Set push exercise stats
            if(exTrigger){
                btnDone.setBackgroundTintList(MyApplication.getContext().getResources().getColorStateList(R.color.btn_yellow));
                btnDone.setTextColor(getResources().getColor(R.color.black));
                btnDone.setOnClickListener(listener);
                exerciseName.setTextColor(getResources().getColor(R.color.btn_yellow));
                exerciseReps.setHint("∞");
            }

            if(i != 0)btnDone.setVisibility(View.INVISIBLE);

            scroll.addView(newLayout);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MyWorkouts.class));
        finish();
    }
}