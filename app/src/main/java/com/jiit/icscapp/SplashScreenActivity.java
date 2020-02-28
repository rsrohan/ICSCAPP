package com.jiit.icscapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        overridePendingTransition(android.R.anim.bounce_interpolator, android.R.anim.fade_out);
        final Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);


        TextView t1 = findViewById(R.id.title);
        TextView t2 = findViewById(R.id.title2);

        ImageView logo = findViewById(R.id.logo);
        //setAlphaAnimation(logo);
        setAlphaAnimation(t1);
        setAlphaAnimation(t2);
//        t1.setAlpha(0f);
//        t2.setAlpha(0f);
//        t1.startAnimation(fadeInAnimation);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                t2.startAnimation(fadeInAnimation);
//            }
//        }, 1500);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 4000);
    }
    public void setAlphaAnimation(View v) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v, "alpha",  1f, .1f);
        fadeOut.setDuration(0);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", .1f, 1f);
        fadeIn.setDuration(4000);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn).after(fadeOut);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        mAnimationSet.start();
    }
    public static void setAlphaAnimation2(View v) {

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", .5f, 1f);
        fadeIn.setDuration(2500);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });
        mAnimationSet.start();
    }
}
