package com.example.bingbindong.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bingbindong.myapplication.Adapter.UserInfo;
import com.example.bingbindong.myapplication.Adapter.UserInfoAdapter;
import com.example.bingbindong.myapplication.EventData.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends Activity {

    Intent intent;
    private Button getAppdataBtn=null;
    private String [] data={"apple","banana","orange", "watermelon","pear","grape","pineapple","strawberry", "cherry","mango"};
    private TextView eventMessageTV = null;

    private Button eeeBtn = null;

    private Button next2Acc = null;


    /**
     * 接收当前Activity跳转后，目标Activity关闭后的回传值
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Toast.makeText(this,requestCode+"",Toast.LENGTH_LONG);
        }
        catch(Exception e)
        {
            Log.d("onActivityResult", "onActivityResult: "+e.getMessage());

        }
//        switch(resultCode){
//            case RESULT_OK:{//接收并显示Activity传过来的值
//                Bundle bundle = data.getExtras();
//                String rs = bundle.getString("test");
//                //tv_main_result.setText(rs);
//                break;
//            }
//            default:
//                break;
//        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        /* 取得Intent中的Bundle对象 */
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String name = bundle.getString("skip");
        Log.i("获取到的name值为",name);

        TextView tv = (TextView)findViewById(R.id.ccc);
        tv.setText(name);

        Button myBtn = (Button)findViewById(R.id.my_button);
        myBtn.setOnClickListener(new MyButtonListener());


        ArrayList<UserInfo> lst = new ArrayList<UserInfo>();

        for(int i=0;i<10;i++)
        {
            UserInfo u = new UserInfo();
            u.set_age("ddd"+i);
            u.set_name("ddd"+i);
            lst.add(u);
        }

        UserInfoAdapter adapter1 = new UserInfoAdapter(lst,this);
        //adapter1.setItemCheckButtonListener(new MyListButtonListener());
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(NextActivity.this,android.R.layout.simple_list_item_1, data);
        ListView listView=(ListView) findViewById(R.id.lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //adapter1.getItem(position);
                TextView v=(TextView) view.findViewById(R.id.my_tv);


                Toast.makeText(NextActivity.this,v.getText()+",item",Toast.LENGTH_SHORT).show();



            }
        });
//
        listView.setAdapter(adapter1);

        getAppdataBtn = (Button)findViewById(R.id.getAppData);
        getAppdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NextActivity.this,getApp().getTextData(),Toast.LENGTH_SHORT).show();
            }
        });


        eventMessageTV = (TextView)findViewById(R.id.ddd);
        EventBus.getDefault().register(this);


        eeeBtn = (Button)findViewById(R.id.eee);
        eeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FirstEvent("FirstEvent btn clicked"));
            }
        });

        //Toast.makeText(NextActivity.this,"123",Toast.LENGTH_SHORT) ;

        next2Acc = (Button) findViewById(R.id.fff);
        next2Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("skip", "我是MainActivity传过来的值！");
                intent.setClass(NextActivity.this, Next2Activity.class);
                startActivityForResult(intent,8);
                Intent intent2 = new Intent();
                intent2.putExtra("skip", "我是MainActivity传过来的值！");
                setResult(11,intent2);
                NextActivity.this.finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onResume() {
        super.onResume();
        App app = (App)getApplication();
        Log.d("NextActivity",app.getCacheName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirstEvent event) {
        /* Do something */
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("harvic", msg);
        //tv.setText(msg);
        eventMessageTV.setText(msg);
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    };

    public App getApp() {
        return ((App)getApplicationContext());
    }

    class MyButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent=new Intent();
            intent.putExtra("test","testvalue");
            intent.setClass(NextActivity.this, MainActivity.class);
            setResult(1,intent);
            NextActivity.this.finish();
        }
    }
}
