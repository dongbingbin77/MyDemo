package com.example.bingbindong.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bingbindong.myapplication.EventData.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MyServiceActivity extends AppCompatActivity {

    MyService myService=null;

    Button finishBTN = null;
    Button startBtn = null;
    Button bindBtn = null;
    Button stop_service = null;
    Button unbind_service = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);




        finishBTN = (Button)findViewById(R.id.finish_button);
        finishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyServiceActivity.this.finish();
            }
        });

        startBtn = (Button)findViewById(R.id.start_service);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyServiceActivity.this.finish();
                /** 进入Activity start服务 */
                Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                startService(intent);
            }
        });

        bindBtn = (Button)findViewById(R.id.bind_service);
        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** 进入Activity bind服务 */
                Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
            }
        });

        stop_service = (Button)findViewById(R.id.stop_service);
        stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** 进入Activity bind服务 */
                Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                //bindService(intent, conn, Context.BIND_AUTO_CREATE);
                stopService(intent);
            }
        });

        unbind_service = (Button)findViewById(R.id.unbind_service);
        unbind_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /** 进入Activity bind服务 */
                //Intent intent = new Intent(MyServiceActivity.this, MyService.class);
                //bindService(intent, conn, Context.BIND_AUTO_CREATE);
                unbindService(conn);
            }
        });

    }

    private ServiceConnection conn = new ServiceConnection() {
        /** 获取服务对象时的操作 */
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            myService = ((MyService.ServiceBinder) service).getService();
            //Toast.makeText(MyServiceActivity.this, "count:"+myService.GetCountValue(), Toast.LENGTH_LONG).show();
            Log.v("count:", myService.GetCountValue()+".");
        }

        /** 无法获取到服务对象时的操作 */
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            myService = null;
        }

    };
    protected void onDestroy() {
        this.unbindService(conn);
        Log.v("MainStadyServics", "out");
        super.onDestroy();

    }



    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirstEvent event) {

        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("harvic", msg);
        //tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

    }

    protected void exitActivity() {
        finish();
        overridePendingTransition(0,
                R.anim.layout_down);
    }

    @Override
    public void onBackPressed() {
        exitActivity();
    }
}
