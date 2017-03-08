package com.example.bingbindong.myapplication;

import android.app.Application;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by bingbin.dong on 2016/10/24.
 */

public class App extends Application {

    public WebView testWebView;

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return cacheName;
    }

    private String cacheName = null;

    @Override
    public void onCreate() {
        Log.d("App","dongbingbin111.onCreate");
        super.onCreate();
        testWebView = new WebView(getApplicationContext());
        testWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        testWebView.loadUrl("http://static.jinjiang.com/opt/static/jjdc/memberRights/platinum-card.html");



        //ViewGroup.LayoutParams lp =  new ViewGroup.LayoutParams(1000,1000);
        //lp.height = 1000;
        //lp.width=1000;
        //testWebView.setLayoutParams(lp);
        testWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {

                //view.requestLayout();
            }
        });

        messageJiguangInit();
    }

    // 极光推送消息初始化
    public void messageJiguangInit() {
        try {
//            JPushInterface.setDebugMode(AppConfig.Debug);    // 设置开启日志,发布时请关闭日志
            JPushInterface.init(getApplicationContext());
            JPushInterface.setAlias(this, ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                    .getDeviceId(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTerminate() {
        Log.d("App","dongbingbin111.onTerminate");
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("App","onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        Log.d("App","onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d("App","onTrimMemory:"+level);
        super.onTrimMemory(level);
    }

    private String textData = "default";

    public void setTextData(String textData) {
        this.textData = textData;
    }
    public String getTextData() {
        return textData;
    }
}
