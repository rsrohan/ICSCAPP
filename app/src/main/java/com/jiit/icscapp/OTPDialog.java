package com.jiit.icscapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPDialog extends Dialog implements DialogInterface.OnClickListener {
    EditText otp;
    Button verifyOTP;
    TextView resendOTP, message;
    private long OTP_TIMEOUT_DURATION = 60;
    Context context;
    private String mVerificationId;
    String phone;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    private String TAG="tag";
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    Activity activity;
    private ProgressDialog progressDialog;

    boolean isNewUser = false;
    public OTPDialog(@NonNull final Context context, String phone, Activity activity) {
        super(context);
        this.context = context;
        this.phone = phone;
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.otpdialog);
        otp = findViewById(R.id.OTP);
        verifyOTP = findViewById(R.id.verify);
        resendOTP = findViewById(R.id.resendOTP);
        progressBar = findViewById(R.id.progressbar);
        message = findViewById(R.id.message);

        mAuth = FirebaseAuth.getInstance();
        phone = "+91"+phone;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            sendVerificationCode(phone);
        }else{
            Toast.makeText(context, "less than KITKAT", Toast.LENGTH_SHORT).show();
        }



        verifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!otp.getText().toString().isEmpty()) {
                    try {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp.getText().toString().trim());
                        signInWithPhoneAuthCredential(credential);

                    } catch (Exception e) {
                        Toast.makeText(context, "onclick" + e, Toast.LENGTH_LONG).show();
                        Log.d(TAG, "onClick: "+e);
                    }

                } else {
                    Toast.makeText(context, "Please enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode(phone);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void sendVerificationCode(String phone) {
        try {
            progressBar.setVisibility(View.VISIBLE);
            verifyOTP.setVisibility(View.GONE);
            otp.setVisibility(View.GONE);
            resendOTP.setVisibility(View.GONE);
            message.setText(R.string.please_wait_while_we_re_detecting_otp_automatically);
            //progressDialog.show();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,        // Phone number to verify
                    OTP_TIMEOUT_DURATION,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    activity,               // Activity (for callback binding)
                    mCallbacks);
        } catch (Exception e) {
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "sendverificationcode: "+e);

        }

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(context, "" + e, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "verification failed: "+e);


        }
        @Override
        public void onCodeAutoRetrievalTimeOut(String s) {
            super.onCodeAutoRetrievalTimeOut(s);
            Toast.makeText(context, "Enter code manually.", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            //progressDialog.dismiss();
            message.setText(R.string.entermanuallymessage);
            verifyOTP.setVisibility(View.VISIBLE);
            otp.setVisibility(View.VISIBLE);
            resendOTP.setVisibility(View.VISIBLE);

        }
        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:" + verificationId);

            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            mResendToken = token;

            // ...
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {


        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    //Toast.makeText(context, "success"+task, Toast.LENGTH_SHORT).show();
                    dismiss();
                    try{
                        if (task.getResult().getAdditionalUserInfo().isNewUser())
                        {
                            isNewUser = true;
                        }
                    }catch (Exception e)
                    {
                        Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(context, "incorrect OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    public boolean isNewUser() {
        return isNewUser;
    }
}