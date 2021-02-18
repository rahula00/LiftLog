package com.example.liftlog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.TimeZone;

public class Profile extends AppCompatActivity {
//    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    DatabaseReference database = firebaseDatabase.getReference().child("Users");
//    FirebaseAuth fAuth = FirebaseAuth.getInstance();
//    String UID = fAuth.getCurrentUser().getUid();
//    FirebaseUser user = fAuth.getCurrentUser();
//    /////// User stuff
//        if (user != null) {
//        // User is signed in
//    } else {
//        // No user is signed in
//    }
//    ///////

    /////TESTING!!!/////////////////////////////////////////////////////////////////////////
    public Queue<ExerciseStats> setQueue(){
        ExerciseStats myExStat = new ExerciseStats(1,0,0,0);
        Queue<ExerciseStats> esQueue = new LinkedList<ExerciseStats>();
        esQueue.add(myExStat);
        return esQueue;
    }

    public Queue<Workout> setWorkout(Queue<ExerciseStats> stats){
        Workout myWorkout = new Workout(1,"workoutName", "description", stats);
        Queue<Workout> workoutQueue = new LinkedList<Workout>();
        workoutQueue.add(myWorkout);
        return workoutQueue;
    }

    String email = "123@gmail.com";
    String name = "Bob";
    java.util.Calendar cal = java.util.Calendar.getInstance(TimeZone.getTimeZone("PST"));
    boolean sex = true;
    Integer feet = 5;
    Integer inches = 10;
    float weight = 160;
    Queue<ExerciseStats> myStats = setQueue();
    Queue<Workout> myWorkout = setWorkout(myStats);
    User myUser = new User(email, name, cal, sex, feet, inches, weight, myWorkout);
    ////////////////////////////////////////////////////////////////////////////////////////


//    User myUser = MyApplication.user;



    ////PROFILE PIC//////////////////////////////////////////////////////////////////////////////
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
                    //startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
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
                //TODO: associate bitmap w user class
                ivImage.setImageBitmap(bmp);
                newProfile = bmp;

            }else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                //TODO: associate bitmap w user class
                ivImage.setImageURI(selectedImageUri);
                try {
                    newProfile = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("damn", "select file broken");
                }
            }

        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////



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






        ///////// Reference to text/check boxes, buttons, etc
        mName = (EditText) findViewById(R.id.name);
        mDob = (EditText) findViewById(R.id.dob);
        mEmail = (EditText) findViewById(R.id.email);
        mFeet = (EditText) findViewById(R.id.feet);
        mInches = (EditText) findViewById(R.id.inches);
        mWeight = (EditText) findViewById(R.id.lbs);
        mMale = (CheckBox) findViewById(R.id.male);
        mFemale = (CheckBox) findViewById(R.id.female);
        mSaveBtn = (Button) findViewById(R.id.btnSave);
        /////////







        scroll = (LinearLayout) findViewById(R.id.linearInScroll);
        ///////////////////////////////////////


        //ai = print out Exercise arrayList
        for( int aI = 0; aI < (MyApplication.exerciseList).size(); aI++) {
            String exerciseName = (MyApplication.exerciseList).get(aI).name;
            Log.d("EXERCISE ARRAY", exerciseName);
        }


        // Layout inflater: inflates layout for each element in Array List
        for( int aI = 0; aI < (MyApplication.exerciseList).size(); aI++) {
            String exerciseFromArray = (MyApplication.exerciseList).get(aI).name;
            LayoutInflater inflater = getLayoutInflater();
            ConstraintLayout newLayout = (ConstraintLayout) inflater.inflate(R.layout.exercise_template, scroll,false);
            TextView exerciseName = (TextView) newLayout.findViewById(R.id.exerciseName);
            exerciseName.setText(exerciseFromArray);
            scroll.addView(newLayout);
        }




        ///////// Current user values, set view texts/checked to user values
        // TODO: grab hints from user
        String nameText = myUser.name;
        mName.setText(nameText);


        java.util.Calendar currentDob = myUser.birthDate;
        int monthHint = currentDob.get(Calendar.MONTH);
        int dayHint = currentDob.get(Calendar.DAY_OF_MONTH);
        int yearHint = currentDob.get(Calendar.YEAR);
        StringBuilder dobHint = new StringBuilder();
//        SimpleDateFormat formatDob = new SimpleDateFormat("MM-dd-YY");
//        String dobHint = formatDob.format(currentDob);
        dobHint.append(monthHint);
        dobHint.append("/");
        dobHint.append(dayHint);
        dobHint.append("/");
        dobHint.append(yearHint);
        mDob.setText(dobHint);


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
        /////////




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

                /////////Things being submitted

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
                Log.d("PROFILE", myUser.name);

                String dobSubmit = mDob.getText().toString().trim();
                if (!TextUtils.isEmpty(dobSubmit)) {
                    myUser.setDate(mYear, mMonth, mDay);
                }


                String ftPre = mFeet.getText().toString().trim();
                String inPre = mInches.getText().toString().trim();
                int ftSubmit = Integer.parseInt(ftPre);
                int inSubmit = Integer.parseInt(inPre);
                if ( (!TextUtils.isEmpty(ftPre)) && (!TextUtils.isEmpty(inPre)) ){
                    myUser.setHeight(ftSubmit, inSubmit);
                }

            }
        });


        // Used to select image on click of profile pic
        ivImage = (ImageView) findViewById(R.id.profile_image);
        // Used to select profile image
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });

        ////////////////////////
        //Calendar Select
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
                        //Will be reformatted
                        String myFormat = "M/dd/yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        yourEditText.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                //mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        /////////////////////////
    }
}