package com.example.bingbindong.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bingbindong.myapplication.EventData.FirstEvent;
import com.example.bingbindong.myapplication.autoupdate.UpdateChecker;
import com.example.bingbindong.myapplication.utils.TimeUtil;
import com.example.bingbindong.myapplication.utils.UserManager;
import com.example.bingbindong.myapplication.utils.jniTest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.LogRecord;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Button my_button = null;
    private Button update_pwd = null;
    private Button my_service_btn = null;

    private Button postEventBtn = null;
    private Button postEventStickyBtn = null;

    private Button RecylerView = null;

    private Button drawRect = null;

    private App app;

    private String[] testArr = {"123", "321", "111", "222"};
    private String testStr = "123";
    private String selectedStars = "ONE,TWO,THREE,FIVE";
    private static Object obj = new Object();
    public static Scheduler getNamedScheduler(final String name) {
        //return Schedulers.from(Executors.newCachedThreadPool(r -> new Thread(r, name)));
        return Schedulers.from(Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, name);
            }
        }));
    }

    public static void threadInfo(String caller) {
        System.out.println(caller + " => " + Thread.currentThread().getName());
    }

    public static void testMoreThread() {
        final List<String> tickets = new Vector<>();
        //final List<String> tickets = new ArrayList<>();
        for(int i =0;i<10000;i++){
            tickets.add("火车票"+i);
        }


        Handler mHandler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

            }
        };



        Thread returnThread=new Thread(){
          public void run(){
              while(true){
                  String s ="车票"+new Random().nextInt();
                  //synchronized (obj) {
                      tickets.add(s);
                  threadInfo(s);
                  //}
                  //threadInfo(s);
                  try {
                      Thread.sleep(1000);
                  }
                  catch (InterruptedException e) {

                  }
              }
          }
        };

        Thread saleThread=new Thread(){
            public void run(){
                while(true){
                    //tickets.add("车票"+new Random().nextInt());
                        //threadInfo(tickets.get(i));
                    //synchronized (obj) {

                        if(tickets.size()>0) {
                            threadInfo(tickets.get(0));
                            tickets.remove(0);
                        }
                    //}

                }
            }
        };
        returnThread.start();
        saleThread.start();

    }

    private static final List<String> scoresFreeCodeArr = Arrays.asList("JJCOM","MRP");


    public static Boolean isScoresFreeCode(String freeCode){
        return scoresFreeCodeArr.contains(freeCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            Thread.sleep(5000);
//        }catch(Exception e){
//
//        }
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        getWindow().setAttributes(params);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //testMoreThread();



//        new AlertDialog.Builder(this)
//                .setTitle("标题")
//                .setMessage("简单消息框")
//                .setPositiveButton("确定", null)
//                .setNegativeButton("取消",null)
//                .show();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = View
                .inflate(this, R.layout.activity_score_booking_tip, null);
        //view.setBackgroundColor(Color.TRANSPARENT);
        //view.setAlpha(0);
        builder.setView(view);

        builder.setCancelable(true);
        TextView title= (TextView) view
                .findViewById(R.id.title);//设置标题
        TextView input_edt= (TextView) view
                .findViewById(R.id.score_free_dialog_message);//输入内容

        TextView btn_cancel=(TextView)view
                .findViewById(R.id.score_free_dialog_cancel_btn);//取消按钮
        TextView btn_comfirm=(TextView)view
                .findViewById(R.id.score_free_dialog_ok_btn);//确定按钮
        //取消或确定按钮监听事件处理
        AlertDialog dialog = builder.create();
        //dialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
        //dialog.getWindow().setBackgroundDrawable(Color.TRANSPARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TimeUtil.isInBetweenTime("14:00:00","17:00:00");

        String TAG="displayMetrics";
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density; //屏幕密度
        int densityDpi = displayMetrics.densityDpi;//屏幕密度dpi
        int heightPixels = displayMetrics.heightPixels;//屏幕高度的像素
        int widthPixels = displayMetrics.widthPixels;//屏幕宽度的像素
        float scaledDensity = displayMetrics.scaledDensity;//字体的放大系数
        float xdpi = displayMetrics.xdpi;//宽度方向上的dpi
        float ydpi = displayMetrics.ydpi;//高度方向上的dpi
        Log.i(TAG, "density = " + density);
        Log.i(TAG, "densityDpi = " + densityDpi);
        Log.i(TAG, "scaledDensity = " + scaledDensity);
        Log.i(TAG, "Screen resolution = " + widthPixels + "×" + heightPixels);
        Log.i(TAG, "xdpi = " + xdpi);
        Log.i(TAG, "ydpi = " + ydpi);


        jniTest jnit = new jniTest();

        float []testArray = {1,2,3};

        jnit.changeFloatFromCpp(testArray,3);

        BigDecimal bd = new BigDecimal(2.0);

        Button my_button2 = (Button)findViewById(R.id.my_button2);
        my_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ConvenientbannerActivity.class);
                startActivity(intent);
            }
        });


        Button my_button3 = (Button)findViewById(R.id.my_button3);
        my_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,VeryfiyCodeActivity.class);
                startActivity(intent);
            }
        });


        Button my_button4 = (Button)findViewById(R.id.my_button4);
        my_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ScoreBookingTipActivity.class);
                startActivity(intent);
            }
        });

        TextView clipToPadding = (TextView)findViewById(R.id.clipToPadding);
        clipToPadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TestClipChildrenActivity.class);
                startActivity(intent);
            }
        });


        Button my_button_slidinguppanel = (Button)findViewById(R.id.my_button_slidinguppanel);
        my_button_slidinguppanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SlidingupPanelActivity.class);
                startActivity(intent);
            }
        });

        //final List<Resources> lst = new ArrayList<Resources>();





        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass() ;

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                //Log.d(tag, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                //Log.d(tag, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                //Log.d(tag, "Error!");
            }
        };
        final List<String> lst1 = new ArrayList<>();
        Observable
                .from(new ArrayList<String>() {{
                    add("A");
                    add("B");
                    add("C");
                    threadInfo("from");
                }})
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        threadInfo("filter");
                        return "A".equals(s) || "B".equals(s);
                    }
                })
                .subscribeOn(getNamedScheduler("11111"))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String aLong) {
                        //LogUtils.d("------->call()" + aLong);
                        threadInfo("aLong");
                        lst1.add(aLong);
                        System.out.println(aLong);
                    }
                });



//        Observable
//                .create(new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> subscriber) {
//                        threadInfo("OnSubscribe.call()");
//                        subscriber.onNext("RxJava");
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        threadInfo(".doOnSubscribe()-1");
//                    }
//                })
//                .subscribeOn(getNamedScheduler("doOnSubscribe1之后的subscribeOn"))
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        threadInfo(".doOnSubscribe()-2");
//                    }
//                })
//                .observeOn(getNamedScheduler("observeOn1"))
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        threadInfo(".doOnSubscribe()-2");
//                    }
//                })
//                .observeOn(getNamedScheduler("observeOn2"))
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        threadInfo(".onNext()");
//                        System.out.println(s + "-onNext");
//                    }
//
//                });


        TestRXJavaThread();

        //HashMap

        TextView tvdd = (TextView) findViewById(R.id.ddd);
        tvdd.setText("hello world\nhello world");


        // DateUtil.getGMTUnixTimeByCalendar();
        //formatGMTUnixTime
        long lll = 1479463890125l;
        DateUtil.getGMTUnixTime(lll);
        DateUtil.getUnixTimeByCalendar();
        List<String> lst = Arrays.asList(testArr);
        if (lst.contains(testStr)) {
            Log.d("testArr", "true");

        } else {
            Log.d("testArr", "false");
        }


        long d1 = (new Date()).getTime();
        long d2 = Calendar.getInstance().getTimeInMillis();
        //(new Date()).getTime() == Calendar.getInstance().getTimeInMillis();

        ArrayList<String> selectArr = new ArrayList<String>(Arrays.asList(selectedStars.split(",")));

        //ONE,TWO,THREE,FOUR,FIVE
        if (selectArr.contains("ONE") || selectArr.contains("TWO")) {
            //去掉单独的ONE TWO,添加一个"ONE,TWO"
            //selectArr.remove("ONE");
            selectArr.remove("ONE");
            selectArr.remove("TWO");

            selectArr.add("ONE,TWO");
        }

        List<String> starItemList = Arrays.asList(new String[]{"ONE,TWO", "THREE"});


        for (int i = 0; i < starItemList.size(); i++) {
            if (!"".equals(starItemList.get(i)) && selectArr.contains(starItemList.get(i))) {
                Log.d("testArr", "==");
            }
        }

        for (int i = 0; i < 10; i++) {
            int v1 = Log.d("vv", "for:" + i);

        }

        Log.d("dd", "dongbingbin");
        my_button = (Button) findViewById(R.id.my_button);
        my_button.setText("Next");
        my_button.setOnClickListener(new MyButtonListener());

        update_pwd = (Button) findViewById(R.id.update_pwd);
        update_pwd.setOnClickListener(new UpdatepwdButtonListener());

        my_service_btn = (Button) findViewById(R.id.service_button);
        my_service_btn.setOnClickListener(new MyserviceButtonListener());

        getApp().setTextData("app 传值");


        postEventBtn = (Button) findViewById(R.id.postEvent);

        postEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FirstEvent("FirstEvent btn clicked"));


                AnimationSet aniSet = new AnimationSet(true);

                AlphaAnimation alA = new AlphaAnimation(1, 0);
                alA.setDuration(1000);
                aniSet.addAnimation(alA);

                TranslateAnimation tl = new TranslateAnimation(0, 300, 0, 300);
                tl.setDuration(3000);
                aniSet.addAnimation(tl);

                my_service_btn.startAnimation(aniSet);


                //EventBus.getDefault().postSticky(
                //      new FirstEvent("FirstEvent btn clicked"));
            }
        });

        postEventStickyBtn = (Button) findViewById(R.id.postStickyEvent);

        postEventStickyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EventBus.getDefault().post(
                //       new FirstEvent("Sticky FirstEvent btn clicked"));

                EventBus.getDefault().postSticky(
                        new FirstEvent("FirstEvent sticky btn clicked"));
                JPushLocalNotification ln = new JPushLocalNotification();
                ln.setBuilderId(0);
                ln.setContent("hhh");
                ln.setTitle("ln");
                ln.setNotificationId(11111111);
                ln.setBroadcastTime(System.currentTimeMillis() + 1000 * 1 * 60);

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", "jpush");
                map.put("test", "111");
                JSONObject json = new JSONObject(map);
                ln.setExtras(json.toString());
                JPushInterface.addLocalNotification(getApplicationContext(), ln);
            }
        });


        RecylerView = (Button) findViewById(R.id.RecylerView);

        RecylerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(MainActivity.this, RecylerViewActivity.class);
                startActivity(intent);
            }
        });


        drawRect = (Button) findViewById(R.id.drawRect);

        drawRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(MainActivity.this, DrawRectActivity.class);
                startActivity(intent);
            }
        });

        EventBus.getDefault().register(this);

        Button to_go_webview = (Button) findViewById(R.id.to_go_webview);
        to_go_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WebView2Activity.class);
                startActivity(intent);
            }
        });

        Button go_to_react = (Button) findViewById(R.id.go_to_react);
        go_to_react.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyReactActivity.class);
                startActivity(intent);
            }
        });


        Button count_down = (Button) findViewById(R.id.count_down);
        count_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateChecker.checkForDialog(MainActivity.this);
            }
        });

        Button window_alert = (Button) findViewById(R.id.window_alert);
        window_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager wManager = (WindowManager) getApplicationContext().getSystemService(
                        Context.WINDOW_SERVICE);
                WindowManager.LayoutParams mParams;// 窗口的属性
                mParams = new WindowManager.LayoutParams();
                mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// 系统提示window
                mParams.format = PixelFormat.TRANSLUCENT;// 支持透明

                //mParams.format = PixelFormat.RGBA_8888;
                mParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;// 焦点
                mParams.width = 800;//窗口的宽和高
                mParams.height = 800;
                mParams.x = 0;//窗口位置的偏移量
                mParams.y = 0;
                //mParams.alpha = 1.1f;//窗口的透明度
                //myView = new MyView(this);
                TextView tv = new TextView(getApplicationContext());
                tv.setBackgroundColor(Color.BLUE);
                tv.setText("testtesttest");
                wManager.addView(tv, mParams);//添加窗口
                //finish();
            }
        });

    }

    private void TestRXJavaThread() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        threadInfo("OnSubscribe.call()");
                        subscriber.onNext("RxJava");
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        threadInfo(".doOnSubscribe()-1");
                    }
                })
                .subscribeOn(getNamedScheduler("doOnSubscribe1之后的subscribeOn"))
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        threadInfo(".doOnSubscribe()-2");
                    }
                })
                .observeOn(getNamedScheduler("observeOn1"))
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        threadInfo(".doOnSubscribe()-2");
                    }
                })
                .observeOn(getNamedScheduler("observeOn2"))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        threadInfo(".onNext()");
                        System.out.println(s + "-onNext");
                    }

                });
    }


    public App getApp() {
        return ((App) getApplicationContext());
    }

    /**
     * 接收当前Activity跳转后，目标Activity关闭后的回传值
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Bundle bundle = data.getExtras();
            String rs = bundle.getString("test");

            TextView tv = (TextView) findViewById(R.id.ccc);
            tv.setText(rs);
        } catch (Exception e) {
            Log.d("onActivityResult", "onActivityResult: " + e.getMessage());

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

    class MyButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.putExtra("skip", "我是MainActivity传过来的值！");
            intent.setClass(MainActivity.this, NextActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    class UpdatepwdButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent intent = new Intent();
                    intent.putExtra("skip", "我是MainActivity传过来的值！");
                    intent.setClass(MainActivity.this, UpdatePWDActivity.class);
                    startActivity(intent);
                    //finish();
                    //overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                    //实现淡入浅出的效果
                    //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                    //由左向右滑入的效果
                    //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

                    //实现zoommin 和 zoomout,即类似iphone的进入和退出时的效果
                    overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                }

            }, 3000);
            //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        }
    }

    class MyserviceButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.putExtra("skip", "我是MainActivity传过来的值！");
            intent.setClass(MainActivity.this, MyServiceActivity.class);
            startActivityForResult(intent, 123);
            overridePendingTransition(R.anim.layout_up1, R.anim.layout_stay1);
        }
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client.connect();
        //AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //AppIndex.AppIndexApi.end(client, getIndexApiAction());
        //client.disconnect();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirstEvent event) {
        /* Do something */
        Toast.makeText(this, event.getMsg(), Toast.LENGTH_LONG).show();
    }

    ;

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEventSticky(FirstEvent event) {
        /* Do something */
        Toast.makeText(this, event.getMsg(), Toast.LENGTH_LONG).show();
    }

    ;

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }


    @Override
    public void onResume() {
        super.onResume();
        app = (App) getApplication();
        app.setCacheName("Application_CacheName");
        UserManager userManager = UserManager.getInstance(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

}


