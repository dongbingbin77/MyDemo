package com.example.bingbindong.myapplication;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private static final int DEFAULT_SHOW_TIME = 3000;//开屏页默认显示时间
    private static int SHOW_TIME = 0;//单位：毫秒
    private CountDownTimer countDownTimer;

    private boolean isToUserGuide = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        startTimer();
    }

    /**
     * 开始计时，计时结束跳转首页
     */
    private void startTimer() {
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        };
        countDownTimer.start();
    }
}
