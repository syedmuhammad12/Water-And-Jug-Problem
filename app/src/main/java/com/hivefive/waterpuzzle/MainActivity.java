package com.hivefive.waterpuzzle;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    TextView name;
    LottieAnimationView animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        name = findViewById(R.id.name);
        animation = findViewById(R.id.animation);

        name.animate().translationY(500).setDuration(2700).setStartDelay(0);
        animation.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(getApplicationContext(),MainMenu.class);
                startActivity(i);
            }
        },4000);
    }
}