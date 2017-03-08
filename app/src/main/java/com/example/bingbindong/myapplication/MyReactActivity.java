package com.example.bingbindong.myapplication;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.example.bingbindong.myapplication.ReactCommunication.RNJavaReactPackage;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.shell.MainReactPackage;

import javax.annotation.Nullable;

public class MyReactActivity extends Activity implements DefaultHardwareBackBtnHandler,ReactInstanceManager.ReactInstanceEventListener {

    private @javax.annotation.Nullable ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .addPackage(new RNJavaReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED).build();
        mReactRootView.startReactApplication(mReactInstanceManager, "HelloWorld", null);
        setContentView(mReactRootView);
        mReactInstanceManager.addReactInstanceEventListener(this);
        //mReactInstanceManager.get();
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }

//        Handler handler = new Handler();
//        Runnable r = new Runnable() {
//            public void run() {
//                //tv.append("Hello World");
//                WritableMap params = Arguments.createMap();
//
//                try {
//                    sendEvent(mReactInstanceManager.getCurrentReactContext(), "hellowDDD", params);
//                }catch(Exception ee){
//
//                    ee.printStackTrace();
//
//                }
//            }
//        };
//        handler.postDelayed(r, 5000);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onReactContextInitialized(ReactContext context) {
        WritableMap params = Arguments.createMap();

        try {
            sendEvent(context, "hellowDDD", params);
        }catch(Exception ee){

            ee.printStackTrace();

        }
    }
}
