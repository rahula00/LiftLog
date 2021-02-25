package com.example.liftlog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;

public class Profile extends AppCompatActivity {
    User myUser = MyApplication.user;

    // Used to select profile image
    private void SelectImage(){
        final CharSequence[] items={"Camera","Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    // Used to select profile image
    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== Activity.RESULT_OK){

            if(requestCode==REQUEST_CAMERA){

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                ivImage.setImageBitmap(bmp);
                newProfile = bmp;

            } else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                ivImage.setImageURI(selectedImageUri);
                try {
                    newProfile = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    EditText mName,mDob, mEmail, mFeet, mInches, mWeight;
    LinearLayout scroll;
    CheckBox mMale, mFemale;
    Button mSaveBtn;
    Integer REQUEST_CAMERA = 1, SELECT_FILE=0;
    EditText yourEditText;
    int mYear, mMonth, mDay;
    ImageView ivImage;
    Bitmap newProfile = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Reference to text/check boxes, buttons, etc
        mName = (EditText) findViewById(R.id.name);
        mDob = (EditText) findViewById(R.id.dob);
        mEmail = (EditText) findViewById(R.id.email);
        mFeet = (EditText) findViewById(R.id.feet);
        mInches = (EditText) findViewById(R.id.inches);
        mWeight = (EditText) findViewById(R.id.lbs);
        mMale = (CheckBox) findViewById(R.id.male);
        mFemale = (CheckBox) findViewById(R.id.female);
        mSaveBtn = (Button) findViewById(R.id.btnSave);

        //Todo: change exerciseArray. Everything below is dependant on this
        scroll = (LinearLayout) findViewById(R.id.linearInScroll);
        ArrayList<Exercise> exerciseArray = (MyApplication.exerciseList);

        //ai = print out Exercise arrayList
        for( int aI = 0; aI < exerciseArray.size(); aI++) {
            String exerciseName = exerciseArray.get(aI).name;
        }

        HashMap maxHashTable = myUser.user_max;
        LayoutInflater inflater = getLayoutInflater();
        // Layout inflater: inflates layout for each element in Array List
        for( int aI = 0; aI < exerciseArray.size(); aI++) {
            // get most recent max weight from user
            int exerciseID = exerciseArray.get(aI).ID;
            StringBuilder exID = new StringBuilder();
            exID.append(exerciseID);
            exID.append("_k");

            int currentWeight = (int) maxHashTable.get(exID.toString());

            String exerciseFromArray = exerciseArray.get(aI).name;
            ConstraintLayout newLayout = (ConstraintLayout) inflater.inflate(R.layout.exercise_template, scroll,false);

            TextView exerciseName = (TextView) newLayout.findViewById(R.id.exerciseName);
            exerciseName.setText(exerciseFromArray);

            TextView maxWeight = (TextView) newLayout.findViewById(R.id.maxWeight);
            maxWeight.setText(String.valueOf(currentWeight));

            ImageView exImg = (ImageView) newLayout.findViewById(R.id.exerciseImage);
            if(exerciseArray.get(aI).image != null) {
                exImg.setImageBitmap(exerciseArray.get(aI).image);
            }

            scroll.addView(newLayout);
        }


        //"spacer" view to add extra space below the scroll list (to be able to get to the bottom)
        if(exerciseArray.size() > 1) {
            ConstraintLayout spacer = (ConstraintLayout) inflater.inflate(R.layout.spacer, scroll, false);
            scroll.addView(spacer);
        }

        // set hints
        String nameText = myUser.name;
        mName.setText(nameText);

        java.util.Calendar currentDob = myUser.birthDate;
        if(currentDob != null) {
            int monthHint = currentDob.get(Calendar.MONTH);
            int dayHint = currentDob.get(Calendar.DAY_OF_MONTH);
            int yearHint = currentDob.get(Calendar.YEAR);
            StringBuilder dobHint = new StringBuilder();
            dobHint.append(monthHint + 1);
            dobHint.append("/");
            dobHint.append(dayHint);
            dobHint.append("/");
            dobHint.append(yearHint);
            mDob.setText(dobHint);
        }
        else {
            mDob.setHint("Birthdate");
        }

        String emailText = myUser.email;
        mEmail.setHint(emailText);

        String feetText = myUser.height.first.toString();
        mFeet.setText(feetText);

        String inchesText = myUser.height.second.toString();
        mInches.setText(inchesText);

        String weightText = Float.toString(myUser.weight);
        mWeight.setText(weightText);

        Boolean userSex = myUser.sex;
        if(userSex) mMale.setChecked(true);
        else if(!userSex)mFemale.setChecked(true);
        else{
            mMale.setChecked(false);
            mFemale.setChecked(false);
        }

        //Unchecks "female" checkbox on click of male checkbox
        mMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFemale.setChecked(false);
            }
        });

        //Unchecks "male" checkbox on click of female checkbox
        mFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMale.setChecked(false);
            }
        });

        // Save Btn on click
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Submit new profile pic if one was chosen
                if(newProfile != null){
                    myUser.setProfile_pic(newProfile);
                }

                Boolean sexSubmit = mMale.isChecked();
                myUser.setSex(sexSubmit);

                String emailSubmit = mEmail.getText().toString().trim();
                if (!TextUtils.isEmpty(emailSubmit)) {
                    myUser.setEmail(emailSubmit);
                }

                String nameSubmit = mName.getText().toString().trim();
                if (!TextUtils.isEmpty(nameSubmit)) {
                    myUser.setName(nameSubmit);
                }

                String dobSubmit = mDob.getText().toString().trim();
                Calendar today = Calendar.getInstance();
                Calendar dob =  Calendar.getInstance();
                dob.set(mYear, mMonth, mDay);
                if(dob.before(today)){
                    if (!TextUtils.isEmpty(dobSubmit)) {
                        myUser.setDate(mYear, mMonth, mDay);
                    }
                }
                else{
                    mDob.setFocusable(true);
                    mDob.setError("This is not a valid birthdate.");
                    mDob.requestFocus();
                    return;
                }

                String ftPre = mFeet.getText().toString().trim();
                String inPre = mInches.getText().toString().trim();
                int ftSubmit = Integer.parseInt(ftPre);
                int inSubmit = Integer.parseInt(inPre);
                if(inSubmit<0 || inSubmit>12){
                    mInches.requestFocus();
                    mInches.setError("Inches must be less than 13.");
                    return;
                }
                else {
                    if ((!TextUtils.isEmpty(ftPre)) && (!TextUtils.isEmpty(inPre))) {
                        myUser.setHeight(ftSubmit, inSubmit);
                    }
                }

                String weightPre = mWeight.getText().toString().trim();
                float weightSubmit = Float.parseFloat(weightPre);
                if ( (!TextUtils.isEmpty(ftPre)) && (!TextUtils.isEmpty(inPre)) ){
                    myUser.setWeight(weightSubmit);
                }

                ArrayList<Exercise> exerciseArray = (MyApplication.exerciseList);
                for (int childPos = 0; childPos < (scroll.getChildCount()-1); childPos++) {
                    int exerciseID = exerciseArray.get(childPos).ID;
                    ConstraintLayout childView = (ConstraintLayout) scroll.getChildAt(childPos);
                    TextView tempView = (TextView) childView.findViewById(R.id.maxWeight);
                    String weightChangedString = tempView.getText().toString().trim();
                    int weightChanged = Integer.parseInt(weightChangedString);
                    StringBuilder exID = new StringBuilder();
                    exID.append(exerciseID);
                    exID.append("_k");
                    myUser.setUser_max(exID.toString(), weightChanged);
                }

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        // Used to select image on click of profile pic
        ivImage = (ImageView) findViewById(R.id.profile_image);

        ///////// Current user values, set view texts/checked to user values
        if(myUser.profile_pic != null) {
            ivImage.setImageBitmap(myUser.profile_pic);
        }

        // Used to select profile image
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });

        // Calendar Select
        yourEditText = (EditText) findViewById(R.id.dob);
        yourEditText.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();

                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(Profile.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "MM/dd/yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        yourEditText.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(myUser.name.equals("")){
            MyApplication.fAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext() , "You have been signed out", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}