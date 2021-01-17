package com.example.liftlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    //TODO: Auto login. Just need to get a Firebase session started. Refer to register.java
    //if(fAuth.getCurrentUser() != null){
    //            startActivity(new Intent(getApplicationContext(), MainActivity.class));
    //            finish();
    //        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void login(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void register(View view){
        startActivity(new Intent(getApplicationContext(),Register.class));
        finish();
    }
    /**
     * Back button listener.
     * Will close the application if the back button pressed twice.
     */
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 4000);
    }
}