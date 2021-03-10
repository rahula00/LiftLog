package com.example.liftlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class num_reps_popup extends Activity {
    Button mOkBtn;
    EditText mNumReps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.numreps_popup);
        mOkBtn = findViewById(R.id.btnOk);
        User myUser = MyApplication.user;

        //On click of 'Ok' button
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumReps = (EditText) findViewById(R.id.numReps);
                String input = mNumReps.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    mNumReps.setError("Number of reps is required.");
                }
                else{
                    int numRepsInput = Integer.parseInt(input);
                    int arrayID = intent.getIntExtra("workout_id", 0);
                    int viewID = intent.getIntExtra("view_id", 0);
                    Intent intent2 = new Intent(MyApplication.getContext(), new_max_weight_popup.class);
                    intent2.putExtra("suggested_increase", NSuns.suggest_increase(numRepsInput));
                    intent2.putExtra("workout_id", arrayID);
                    intent2.putExtra("view_id", viewID);
                    startActivity(intent2);
                    finish();
                }
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.2));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
    }

    @Override
    public void onBackPressed() {}
}
