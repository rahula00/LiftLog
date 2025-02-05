package com.example.liftlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class new_max_weight_popup extends Activity {
    Button mOkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.newmaxweight_popup);
        User myUser = MyApplication.user;

        int suggested_increase = intent.getIntExtra("suggested_increase", 0);
        int arrayID = intent.getIntExtra("workout_id", 0);
        int viewID = intent.getIntExtra("view_id", 0);

        // display current max
        LinkedList<ExerciseStats> exerciseArray = myUser.user_workouts.get(arrayID).statsList;
        String exKey = String.valueOf(exerciseArray.get(viewID).exercise);
        TextView currentMax = (TextView) findViewById(R.id.currMaxWeight);
        currentMax.setText(myUser.user_max.get(exKey+"_k").toString());

        // display suggested increase
        TextView suggestedIncrease = (TextView) findViewById(R.id.suggestedIncrease);
        suggestedIncrease.setText("" + suggested_increase);

        mOkBtn = findViewById(R.id.secondBtnOk);

        //On click of 'Ok' button
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // take new max weight inputted by user
                EditText mNewMaxWeight = (EditText) findViewById(R.id.newMaxWeight);
                String input = mNewMaxWeight.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    mNewMaxWeight.setError("New max weight is required.");
                }
                else{
                    int newMaxWeight = Integer.parseInt(input);
                    if(newMaxWeight < 0){
                        mNewMaxWeight.setError("Max weight greater than 0 is required.");
                    }
                    else{
                        myUser.user_max.put((exKey + "_k"), newMaxWeight);
                        myUser.updateToFirebase();
                        finish();
                    }
                }
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = 620;
        Window view = getWindow();
        view.setLayout((int)(width*.92), height);
        view.setBackgroundDrawableResource(R.drawable.roundedges);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;
        getWindow().setAttributes(params);
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        int arrayID = intent.getIntExtra("workout_id", 0);
        int viewID = intent.getIntExtra("view_id", 0);
        Intent intent2 = new Intent(MyApplication.getContext(), num_reps_popup.class);
        intent2.putExtra("workout_id", arrayID);
        intent2.putExtra("view_id", viewID);
        startActivity(intent2);
        finish();
    }
}
