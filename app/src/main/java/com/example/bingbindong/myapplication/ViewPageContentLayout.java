package com.example.bingbindong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewPageContentLayout extends AppCompatActivity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_content_layout);
    }

}
