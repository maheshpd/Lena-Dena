package com.example.lenadena.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.lenadena.R;

public class Splash extends AppCompatActivity {

    TextView lena,dena;
    Animation leftAnimation,rightAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        lena = findViewById(R.id.lena);
        dena = findViewById(R.id.dena);

        leftAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_left);
        rightAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);

        lena.startAnimation(leftAnimation);
        dena.startAnimation(rightAnimation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();

            }
        }, 5000);
    }
}
