package com.example.liftlog;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mRegisterBtn;
    FirebaseAuth fAuth;
    String userID;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get matching data from XML front-end
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegisterBtn = findViewById(R.id.btnLogin);

        //Open instance of Firebase
        fAuth = FirebaseAuth.getInstance();

        //Spinny bar go brrrr
        progressBar = findViewById(R.id.progressBar);

        //Listen on the Register button, then do appropriate checks
        //If basic checks pass, pass to firebase and check against users
        //Respond accordingly (login / return error)
        mRegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //Ensure data is actually passed
                //TODO: Check for actual email
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }

                //TODO: Check for password security?
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be greater or equals to 6 characters.");
                }

                //BRRRRRR, SPIN
                progressBar.setVisibility(View.VISIBLE);

                //Creates the user in the firebase with email and password
                //Listens to the response, if succeed or failed
                //Duplicates returned as failures
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //If successfully created the user, send an verification email to that email
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();

                            //Get the new user and send an email to that email.
                            //Log out the new user to prevent shenanigans
                            //Then redirect back to login page
                            FirebaseUser user = fAuth.getCurrentUser();
                            user.sendEmailVerification();
                            fAuth.signOut();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        }
                        //If it failed, pass in the error that is returned and prompt to go again
                        else{
                            Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }

    //Handle back buttonfunctionalities.
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Login.class));
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