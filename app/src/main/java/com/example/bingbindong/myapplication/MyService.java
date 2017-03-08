package com.example.bingbindong.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.bingbindong.myapplication.EventData.FirstEvent;

/**
 * Created by bingbin.dong on 2016/10/24.
 */

public class MyService extends Service {
    /** 创建参数 */
    boolean threadDisable;
    int count;
    @Override
    public void onCreate() {
        super.onCreate();
        /** 创建一个线程，每秒计数器加一，并在控制台进行Log输出 */
        new Thread(new Runnable() {
            public void run() {
                while (!threadDisable) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    count++;
                    Log.v("CountService", "Count is" + count);
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind.....");
        IBinder result = null;
        if ( null == result ) result = new ServiceBinder() ;
        Toast.makeText(this, "onBind",Toast.LENGTH_LONG);
        return result;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public int GetCountValue()
    {
        return count;
    }

    //此方法是为了可以在Acitity中获得服务的实例
    class ServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }



}
