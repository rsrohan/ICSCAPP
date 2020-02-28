package com.jiit.icscapp;

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

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.jiit.icscapp.R;

import java.util.Objects;

public class ContactUsFragment extends Fragment {

    private TextView institute, address, email, website, epbax, phone, fax;
    private Button call, direction, contactcall, contactMail, contactWebsite;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);

        //institute = root.findViewById(R.id.institute);
        //address = root.findViewById(R.id.address);
        email = root.findViewById(R.id.email);
        website = root.findViewById(R.id.website);
        epbax = root.findViewById(R.id.epbax);
        phone = root.findViewById(R.id.phone);
        fax = root.findViewById(R.id.fax);
        call = root.findViewById(R.id.callbtn);
        direction = root.findViewById(R.id.direction);

        contactcall = root.findViewById(R.id.contactCall);
        contactMail = root.findViewById(R.id.contactMail);
        contactWebsite = root.findViewById(R.id.contactWebsite);

//        institute.setText("Jaypee Institute of Information Technology, ");
//        address.setText("JIIT Sector - 62, Noida - 201309");
        email.setText("icsc@jiit.ac.in");
        website.setText("www.jiit.ac.in");
        epbax.setText("(+91)-120-2400973-976");
        phone.setText("(+91)-120-2594322");
        fax.setText("0120 - 2400986, 0120 - 2401006");

        call.setOnClickListener(new View.OnClickListener() {
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

        contactcall.setOnClickListener(new View.OnClickListener() {
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

        contactMail.setOnClickListener(new View.OnClickListener() {
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

        contactWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.jiit.ac.in/";
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(url));
                startActivity(web);
            }
        });
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav = new Intent(getActivity(), MapsActivity.class);
                startActivity(nav);
            }
        });

        return root;
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),new String[] {Manifest.permission.CALL_PHONE}, 1);
    }
}