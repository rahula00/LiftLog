package com.example.liftlog;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Pulls the relevant data from the XML page
        //Linked to the relevant field
        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText)findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.btnLogin);
        mCreateBtn = findViewById(R.id.textRegister);



        //On click of 'login' button
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: PLEASE SANITIZE THIS. FOR THE LOVE OF GOD THIS IS A MISTAKE AS IS!!
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //Check for email
                //TODO: Check for valid email
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                //Check for password
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                //Check for password length
                //TODO:Actual password strength check?
                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                //Spinny bar go BRRRR
                progressBar.setVisibility(View.VISIBLE);

                //authenticate the user
                //handshake to firebase database
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //If successfully authenticated user with credentials given
                        //Then check if email is verified
                        //Then check if the profile is set up
                        //Navigate to the profile page or main page respectively
                        if(task.isSuccessful()){
                            FirebaseUser user = fAuth.getCurrentUser();
                            //If email was verified, then allow login
                            if(user.isEmailVerified()) {
                                Toast.makeText(Login.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                                //Grab the UID and check if the user has a name set up in their profile
                                //This is how querying for data works in this language... Its scuff
                                String UID = user.getUid();
                                DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("Users").child(UID);
                                dataRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        MyApplication.user = new User(dataSnapshot);
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        finish();

                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.w("Login", "onCancelled", databaseError.toException());
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });

                            //If email was unverified, then refuse login and remove instance token
                            }else {
                                Toast.makeText(Login.this, "Please Verify Your Account", Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().signOut();
                            }
                        //Was not successful in authenticating the user
                        //Prints out technical error, TODO:Replace with generic error message rather then the the debug response
                        }else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

        //If user clicks on the create button, move to register page
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 // view is our View we trying to animate
                startActivity(new Intent(getApplicationContext(),Register.class));
                finish();
            }
        });



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