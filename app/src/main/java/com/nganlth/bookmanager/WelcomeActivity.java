package com.nganlth.bookmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class WelcomeActivity extends AppCompatActivity {
    public static int SPLASH_SCREEN = 6000;
    //Khai báo biến
    Animation topAnim, bottomAnim;
    LottieAnimationView ivWelcome;
    TextView tvBookmanager, tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Tham chiếu
        ivWelcome = findViewById(R.id.ivWelcome);
        tvBookmanager = findViewById(R.id.tvBookmanager);
        tvWelcome = findViewById(R.id.tvWelcome);
        // Set Animation
        tvBookmanager.setAnimation(bottomAnim);
        tvWelcome.setAnimation(bottomAnim);
        //Set animation
        ivWelcome.animate().translationY(-1600).setDuration(1000).setStartDelay(5000);
        tvBookmanager.animate().translationY(1400).setDuration(1000).setStartDelay(5000);
        tvWelcome.animate().translationY(1600).setDuration(1000).setStartDelay(5000);
        // Chuyển hướng sang Login

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
