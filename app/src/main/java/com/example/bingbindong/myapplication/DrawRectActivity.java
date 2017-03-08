package com.example.bingbindong.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class DrawRectActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainLayout(this));
    }

}
class MainLayout extends RelativeLayout
{


    public MainLayout(Context context)
    {
        super(context);
        setWillNotDraw(false);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        setBackgroundColor(Color.WHITE);
//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        canvas.drawRect(new Rect(0, 0, 570, 320), paint);
//
//
//        Paint paint1 = new Paint();
//        paint1.setColor(Color.RED);
//        canvas.drawRect(new Rect(0, 100, 570, 320), paint1);


        Paint paint3 = new Paint();
        paint3.setColor(Color.GREEN);
        Path path = new Path();
        path.cubicTo(0.0f,0.0f,100.0f,100.0f,300.0f,300.0f);
//        path.lineTo(100,100);
//        path.lineTo(100, 200);
//        path.lineTo(150, 250);
        canvas.drawPath(path,paint3);

//        Paint paint2 = new Paint();
//        paint2.setColor(Color.GREEN);
//        canvas.drawRect(new Rect(0, 100, 570, 320), paint2);
    }
}
