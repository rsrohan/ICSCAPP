package com.example.icscapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.icscapp.R;

import java.util.Objects;

public class AboutUsFragment extends Fragment {


    private TextView aboutPhone, aboutEmail;
    private Button callBtn, emailBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about_us, container, false);

        aboutEmail = root.findViewById(R.id.aboutEmail);
        aboutPhone = root.findViewById(R.id.aboutphone);
        callBtn = root.findViewById(R.id.aboutCallBtn);
        emailBtn = root.findViewById(R.id.aboutMailBtn);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callbtn = new Intent(Intent.ACTION_DIAL);
                String number = getString(R.string.Authority_number);

                callbtn.setData(Uri.parse("tel:" + number));

                if(ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getActivity(),"please grant permission to call", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else{
                    startActivity(callbtn);
                }
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "webadmin@jiit.ac.in" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, ""));
            }
        });

        aboutPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callbtn = new Intent(Intent.ACTION_DIAL);
                String number = getString(R.string.Authority_number);

                callbtn.setData(Uri.parse("tel:" + number));

                if(ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getActivity(),"please grant permission to call", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else{
                    startActivity(callbtn);
                }
            }
        });

        aboutEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "webadmin@jiit.ac.in" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, ""));

            }
        });

        return root;
    }


    private void requestPermission(){
        ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),new String[] {Manifest.permission.CALL_PHONE}, 1);
    }


}