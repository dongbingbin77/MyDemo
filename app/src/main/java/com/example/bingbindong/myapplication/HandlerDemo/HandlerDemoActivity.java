package com.example.bingbindong.myapplication.HandlerDemo;

import android.graphics.Color;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.bingbindong.myapplication.MainActivity;
import com.example.bingbindong.myapplication.R;

import java.lang.ref.WeakReference;

public class HandlerDemoActivity extends AppCompatActivity {

    private Button mTvServiceInfo;
    private HandlerThread mCheckMsgThread;
    private Handler mCheckMsgHandler;
    private boolean isUpdateInfo;

    private static final int MSG_UPDATE_INFO = 0x110;

    //与UI线程管理的handler
    private Handler mHandler = new Handler();

    private final Handler mLeakyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // ...
            Log.d("handleMessage", "handleMessage: ");
        }
    };

    private static class MyNoLeakHandler extends Handler{
        private final WeakReference<HandlerDemoActivity> mActivity;
        public MyNoLeakHandler(HandlerDemoActivity activity){
            mActivity = new WeakReference<HandlerDemoActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("handleMessage", mActivity.getClass().getName());
        }

        @Override
        public String getMessageName(Message message) {
            return super.getMessageName(message);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);
        mainThreadCallSubThread();

        handlerRunOnNewThread();
//        MyNoLeakHandler myHandlerNoLeak = new MyNoLeakHandler(this);
//        myHandlerNoLeak.sendMessageDelayed(Message.obtain(),5000);
//        //mLeakyHandler.sendMessageDelayed(Message.obtain(),5000);
//        finish();
    }
    private Handler handler;
    //在新的线程上创建handler，在主线程上sendmessage，handler仍然在新县城上跑
    private void handlerRunOnNewThread(){
        new Thread("Test Thread1")
        {

            public void run()
            {
                Looper.prepare();
                //Log.d("Test Thread",Thread.currentThread().getName());
                handler = new Handler()
                {
                    public void handleMessage(android.os.Message msg)
                    {
                        Log.d("Test Thread",Thread.currentThread().getName());
                    };
                };

                Looper.loop();
            }
        }.start();
        try {
            Thread.sleep(5000);
        }catch(Exception e){

        }
        handler.sendMessageDelayed(Message.obtain(),2000);
    }

    //两个线程的handler互相调用
    private void mainThreadCallSubThread(){
        //创建后台线程
        initBackThread();

        mTvServiceInfo = (Button)findViewById(R.id.handler_demo_my_button);

    }
    private void initBackThread()
    {
        mCheckMsgThread = new HandlerThread("check-message-coming");
        mCheckMsgThread.start();
        mCheckMsgHandler = new Handler(mCheckMsgThread.getLooper())
        {
            @Override
            public void handleMessage(Message msg)
            {
                Log.d("Test Thread",Thread.currentThread().getName());
                checkForUpdate();
                if (isUpdateInfo)
                {
                    mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
                }
            }
        };


    }

    /**
     * 模拟从服务器解析数据
     */
    private void checkForUpdate()
    {
        try
        {
            //模拟耗时
            Thread.sleep(1000);
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    String result = "实时更新中，当前大盘指数：<font color='red'>%d</font>";
                    result = String.format(result, (int) (Math.random() * 3000 + 1000));
                    mTvServiceInfo.setText(result);
                }
            });

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //停止查询
        isUpdateInfo = false;
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //释放资源
        mCheckMsgThread.quit();
    }


    private void leakDemo1(){
        mLeakyHandler.sendMessageDelayed(Message.obtain(),5000);
    }

    private void leakDemo2(){
        Handler mLeakyHandler2 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("handleMessage", "handleMessage: ");
                //Button button = (Button)MainActivity.this.findViewById(R.id.my_button3);
                //button.setBackgroundColor(Color.RED);
                HandlerDemoActivity.this.findViewById(R.id.handler_demo_my_button).setBackgroundColor(Color.RED);
            }
        };
        mLeakyHandler2.sendMessageDelayed(Message.obtain(),5000);
    }
}
