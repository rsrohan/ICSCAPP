package com.example.icscapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpeakerDetails extends AppCompatActivity {
    String name, image, about, from;
    private TextView speakerName, speakerAbout, speakerFrom;
    private ImageView speakerImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_details);


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        name = bundle.getString("NAME");
        image = bundle.getString("IMAGE");
        about = bundle.getString("ABOUT");
        from = bundle.getString("FROM");


        speakerName = findViewById(R.id.speakerName);
        speakerAbout = findViewById(R.id.speakerInfo);
        speakerFrom = findViewById(R.id.speakerCountry);
        speakerImg = findViewById(R.id.speakerimg);

        speakerName.setText(name);
        speakerAbout.setText(about);
        speakerFrom.setText(from);
        Glide.with(getApplicationContext()).load(Uri.parse(image)).into(speakerImg);
        //speakerImg.setImageURI(Uri.parse(image));


    }
}
