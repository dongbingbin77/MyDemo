package com.example.bingbindong.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class WebView2Activity extends AppCompatActivity {

    LinearLayout webview_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        App appApplication = (App) getApplication();
        webview_id = ((LinearLayout)findViewById(R.id.webview_id));

        webview_id.addView(appApplication.testWebView,0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webview_id.removeAllViews();
    }
}
