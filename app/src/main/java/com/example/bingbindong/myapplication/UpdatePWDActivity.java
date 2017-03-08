package com.example.bingbindong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UpdatePWDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
        this.getSupportActionBar().hide();

    }

    @Override
    public void finish() {
        super.finish();
        //实现zoommin 和 zoomout,即类似iphone的进入和退出时的效果
        overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
    }
}
