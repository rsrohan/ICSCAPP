package com.example.icscapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText paperId, phoneNumber;
    Button loginButton, dob;
    Activity activity;

    String regEx = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";


    DatabaseReference databaseReference;
    private String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        paperId = findViewById(R.id.paperId);
        loginButton = findViewById(R.id.login);
        //dob = findViewById(R.id.selectDOB);
        phoneNumber = findViewById(R.id.phoneNumber);
        activity = this;
        databaseReference = FirebaseDatabase.getInstance().getReference("PROFILES");
//        dob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                if (!phoneNumber.getText().toString().equals("") && !paperId.getText().toString().equals("")) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(activity);
                    datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                            try {
                                if (!phoneNumber.getText().toString().equals("") && !paperId.getText().toString().equals("")) {
                                    databaseReference.child("" + paperId.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            try {
                                                if (dataSnapshot.getValue(String.class).trim().equals(dayOfMonth + "/" + (month + 1) + "/" + year)) {
//                                                startActivity(new Intent(activity, MainActivity.class));
//                                                finish();
                                                    if (Pattern.compile(regEx).matcher(phoneNumber.getText().toString()).matches()) {
                                                        final OTPDialog otpDialog = new OTPDialog(LoginActivity.this, phoneNumber.getText().toString(), activity);
                                                        otpDialog.setCancelable(false);
                                                        otpDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                            @Override
                                                            public void onDismiss(DialogInterface dialog) {

                                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                                finish();
                                                            }
                                                        });
                                                        otpDialog.show();

                                                    } else {
                                                        Toast.makeText(LoginActivity.this, "ARE YOU SURE THIS NUMBER IS VALID ?", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(activity, "INCORRECT DOB.", Toast.LENGTH_SHORT).show();

                                                }
                                            } catch (Exception e) {
                                                Toast.makeText(activity, "PAPER ID MAY NOT EXIST. PLEASE VERIFY", Toast.LENGTH_SHORT).show();
                                                Log.d(TAG, "onDataChange: " + e);
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(activity, "FILL ALL DETAILS", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(activity, "paperId not found. " + e, Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onDateSet: " + e);
                            }

                        }
                    });
                    datePickerDialog.show();
                    Toast.makeText(activity, "ENTER YOUR DATE OF BIRTH", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
