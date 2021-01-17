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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    String userID;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Creates the back arrow top left
        //TODO: Do we actually even want this when android has a back?
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Get matching data from XML front-end
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.btnLogin);
        mLoginBtn = findViewById(R.id.textRegister);

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
                        //If successfully created the user, log in and return message that it worked
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

        //Listens on buttons to return to home/login and returns you there
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }

    //Handle back button and back arrow functionalities.
    //TODO: Remove back arrow? Add to page stack?
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }
}