package com.example.icscapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class NavigateFragment extends Fragment {

    private TextView about;
    private Button navigate_Button, click_call, click_location, click_mail  ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.naviate_fragment, container, false);

        navigate_Button = root.findViewById(R.id.navigatebtn);
        click_call = root.findViewById(R.id.clickcall);
        click_location = root.findViewById(R.id.clickLocation);
        click_mail = root.findViewById(R.id.clickmail);

        navigate_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tomaps = new Intent(getActivity(), MapsActivity.class);
                startActivity(tomaps);
            }
        });

        click_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callbtn = new Intent(Intent.ACTION_DIAL);
                String number = "7388263171";

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

        click_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tomaps = new Intent(getActivity(), MapsActivity.class);
                startActivity(tomaps);
            }
        });

        click_mail.setOnClickListener(new View.OnClickListener() {
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
