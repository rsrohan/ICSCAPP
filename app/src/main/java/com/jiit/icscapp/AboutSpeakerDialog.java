package com.jiit.icscapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class AboutSpeakerDialog extends Dialog implements DialogInterface.OnClickListener {

    String text;
    TextView about;
    public AboutSpeakerDialog(@NonNull Context context, String text) {
        super(context);
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_speaker_dialog);
        about = findViewById(R.id.aboutSpeaker);
        about.setText(text);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
