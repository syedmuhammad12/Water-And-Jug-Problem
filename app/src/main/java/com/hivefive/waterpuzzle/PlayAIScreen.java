package com.hivefive.waterpuzzle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PlayAIScreen extends AppCompatActivity {

    int delay = 3000;
    TextView scr1, scr2, scr3;
    ProgressBar pb1, pb2, pb3;
    int jug1, jug2, target, progress1, progress2, progress3, check1, check2, check3;
    Result result;
    Pair results;
    int k = 0;
    CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_aiscreen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        scr1 = findViewById(R.id.main_jar);
        scr2 = findViewById(R.id.jar1);
        scr3 = findViewById(R.id.jar2);
        pb1 = findViewById(R.id.progressBar);
        pb2 = findViewById(R.id.progressBar1);
        pb3 = findViewById(R.id.progressBar2);
        jug1 = getIntent().getIntExtra("res_j1", 0);
        jug2 = getIntent().getIntExtra("res_j2", 0);
        result = (Result) getIntent().getSerializableExtra("results");
        results = result.main_res;

        scr2.setText("00/"+jug1);
        scr3.setText("00/"+jug2);

        pb1.setMax(20);
        pb2.setMax(jug1);
        pb3.setMax(jug2);


        int n =results.path.size();




        for(int i = 0; i<n ; i++){

            k = 0;
            int finalI = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    mCountDownTimer=new CountDownTimer(delay-250,1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
//                            mProgressBar.setProgress((int)i*100/(5000/1000));

                            progress1 = pb1.getProgress();
                            progress2 = pb2.getProgress();
                            progress3 = pb3.getProgress();

                            check2 = results.path.get(finalI).j1;
                            check3 = results.path.get(finalI).j2;
                            check1 = 20 - (check2 + check3);
                            k++;
                            if (progress1 > check1) {

                                pb1.setProgress(progress1 - ((int) k*check1/((delay-250)/1000)));

                            } else{

                                pb1.setProgress(progress1 + ((int) k*check1/((delay-250)/1000)));

                            }


                            if (progress2 > check2) {

                                pb2.setProgress(progress2 - ((int) k*check2/((delay-250)/1000)));

                            } else{

                                pb2.setProgress(progress2 + ((int) k*check2/((delay-250)/1000)));

                            }

                            if (progress3 > check3) {

                                pb3.setProgress(progress3 - ((int) k*check3/((delay-250)/1000)));

                            } else{

                                pb3.setProgress(progress3 + ((int) k*check3/((delay-250)/1000)));

                            }




                        }

                        @Override
                        public void onFinish() {

                            pb1.setProgress(check1);
                            pb2.setProgress(check2);
                            pb3.setProgress(check3);

                            scr1.setText(check1 + "/" + 20);
                            scr2.setText(check2 + "/" + jug1);
                            scr3.setText(check3 + "/" + jug2);

                        }
                    };
                    mCountDownTimer.start();




                }
            }, delay);


        }















    }
}