package com.example.liftlog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
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

        Workout testW = new Workout(1, "myWorkout", "get big or die trying");
        ExerciseStats bench = new ExerciseStats(0, 150, 5, 1);
        ExerciseStats squat = new ExerciseStats(1, 225, 5, 1);
        ExerciseStats deadlift = new ExerciseStats(2, 300, 5, 1);
        ExerciseStats overheadpress = new ExerciseStats(3, 100, 5, 1);
        squat.trigger_max_change = true;

        //TODO: set exerciseArray to the proper array
        LinkedList<ExerciseStats> exerciseArray = testW.statsList;
        exerciseArray.add(bench);
        exerciseArray.add(squat);
        exerciseArray.add(deadlift);
        exerciseArray.add(overheadpress);

        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForColorStateLists")
            public void onClick(View view) {

                int viewID = (int) view.getTag();
                if(!checkList.contains(viewID)){
                    checkList.add(viewID);
                    view.setBackgroundTintList(MyApplication.getContext().getResources().getColorStateList(R.color.red));
                    Button b = (Button) view;
                    b.setText("Confirm");
                    b.setTextSize(10);
                }
                else {
                    for (int i = 0; i < scroll.getChildCount(); i++) {

                        View v = scroll.getChildAt(i);
                        int compareID = (int) v.getTag();

                        TextView exerciseName = (TextView) v.findViewById(R.id.exerciseName);
                        boolean tempTrigger = (boolean) exerciseName.getTag();

                        if (compareID == viewID) {
                            exerciseArray.remove(viewID);
                            scroll.removeView(v);
                            checkList.remove(viewID);
                        }

                    }
                }
            }
        };

        View.OnClickListener listenerForMax = new View.OnClickListener() {
            public void onClick(View view) {
                int viewID = (int) view.getTag();
                for (int i = 0; i < scroll.getChildCount(); i++) {
                    View v = scroll.getChildAt(i);
                    int compareID = (int) v.getTag();
                    TextView exerciseName = (TextView) v.findViewById(R.id.exerciseName);
                    if (compareID == viewID) {
                        TextView tempReps = (TextView) v.findViewById(R.id.exerciseReps);
                        String maxRepsString = tempReps.getText().toString().trim();
                        if (!TextUtils.isEmpty(maxRepsString)) {
                            scroll.removeView(v);
                            int maxReps = Integer.parseInt(maxRepsString);
                            String exKey = String.valueOf(exerciseArray.get(viewID).exercise);
                            //TODO: add "_k" to each key???
                            //TODO: update max (when testing with actual user)
                            //myUser.user_max.put(exKey, maxReps);
                            exerciseArray.remove(viewID);

                        } else {
                            Log.d("Where the reps at??", "enter some reps");
                            Toast.makeText(MyApplication.getContext(), "Please enter your reps!", Toast.LENGTH_LONG).show();
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

            //KEY:
            // newLayout -> ID
            // exerciseName -> trigger_max_change
            // btn -> ID

            ConstraintLayout newLayout = (ConstraintLayout) inflater.inflate(R.layout.workout_template, scroll,false);
            newLayout.setTag(exID);

            TextView exerciseName = (TextView) newLayout.findViewById(R.id.exerciseName);
            String exerciseNameTemp = MyApplication.exerciseList.get(exEx).name;
            exerciseName.setText(exerciseNameTemp);
            exerciseName.setTag(exTrigger);

            TextView exerciseWeight = (TextView) newLayout.findViewById(R.id.exerciseWeight);
            exerciseWeight.setText(String.valueOf(exWeight));

            TextView exerciseReps = (TextView) newLayout.findViewById(R.id.exerciseReps);
            if(!exTrigger) exerciseReps.setText(String.valueOf(exReps));

            TextView exerciseSets = (TextView) newLayout.findViewById(R.id.exerciseSets);
            exerciseSets.setText(String.valueOf(exSets));

            Button btnDone = (Button) newLayout.findViewById(R.id.btnDone);
            btnDone.setTag(exID);
            if(!exTrigger) btnDone.setOnClickListener(listener);

            // Set push exercise stats
            if(exTrigger){
                btnDone.setBackgroundTintList(MyApplication.getContext().getResources().getColorStateList(R.color.btn_yellow));
                btnDone.setTextColor(getResources().getColor(R.color.black));
                btnDone.setOnClickListener(listenerForMax);
                exerciseName.setTextColor(getResources().getColor(R.color.btn_yellow));
                exerciseReps.setHint("∞");
                exerciseReps.setClickable(true);
                exerciseReps.setFocusable(true);
                exerciseReps.setFocusableInTouchMode(true);
                exerciseReps.setCursorVisible(true);
            }

            scroll.addView(newLayout);
        }



    }
}