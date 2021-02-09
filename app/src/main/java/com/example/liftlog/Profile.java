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
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Profile extends AppCompatActivity {


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

            }else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                //TODO: associate bitmap w user class
                ivImage.setImageURI(selectedImageUri);
            }

        }
    }

    EditText mName,mDob, mEmail, mFeet, mInches, mWeight;
    LinearLayout scroll;
    CheckBox mMale, mFemale;
    Button mSaveBtn;
    TextView mMaxWeight;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Integer REQUEST_CAMERA = 1, SELECT_FILE=0;
    EditText yourEditText;
    int mYear, mMonth, mDay;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ///////// Reference to text/check boxes
        mName = (EditText) findViewById(R.id.name);
        mDob = (EditText) findViewById(R.id.dob);
        mEmail = (EditText) findViewById(R.id.email);
        mFeet = (EditText) findViewById(R.id.feet);
        mInches = (EditText) findViewById(R.id.inches);
        mWeight = (EditText) findViewById(R.id.lbs);
        mMale = (CheckBox) findViewById(R.id.male);
        mFemale = (CheckBox) findViewById(R.id.female);
        /////////

        //////// layout reference
        scroll = (LinearLayout) findViewById(R.id.linearInScroll);

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout newLayout = (LinearLayout) inflater.inflate(R.layout.exercise_template, scroll,true);
        TextView exerciseName = (TextView) newLayout.findViewById(R.id.exerciseName);
        exerciseName.setText("Bench Press");
        LinearLayout newLayout2 = (LinearLayout) inflater.inflate(R.layout.exercise_template, scroll,true);
        TextView exerciseName2 = (TextView) newLayout2.findViewById(R.id.exerciseName);
        exerciseName2.setText("Back Squat");
        LinearLayout newLayout3 = (LinearLayout) inflater.inflate(R.layout.exercise_template, scroll,true);
        TextView exerciseName3 = (TextView) newLayout3.findViewById(R.id.exerciseName);
        exerciseName3.setText("Deadlift");
        LinearLayout newLayout4 = (LinearLayout) inflater.inflate(R.layout.exercise_template, scroll,true);
        TextView exerciseName4 = (TextView) newLayout4.findViewById(R.id.exerciseName);
        exerciseName4.setText("Overhead Press");



//        TextView tvContent = (TextView) layout.findViewById(R.id.tvContent);

        ////////

        ///////// Current user values, set view texts/checked to user values
        // TODO: grab hints from user
        String nameText = "NameHint";
        mName.setText(nameText);

        String dobText = "DobHint";
        mDob.setText(dobText);

        String emailText = "EmailHint";
        mEmail.setText(emailText);

        String feetText = "ft";
        mFeet.setText(feetText);

        String inchesText = "in";
        mInches.setText(inchesText);

        String weightText = "lb";
        mWeight.setText(weightText);

        Boolean sex = true;
        if(sex) mMale.setChecked(true);
        else if(!sex)mFemale.setChecked(true);
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
                        String myFormat = "dd/MM/yy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
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