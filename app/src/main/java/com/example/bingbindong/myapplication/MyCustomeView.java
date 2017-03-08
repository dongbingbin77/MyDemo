package com.example.bingbindong.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class MyCustomeView extends View {

    private Paint mPaint;

    public MyCustomeView(Context context) {
        super(context);
        mPaint = new Paint();
    }


    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);                           //设置画笔为无锯齿
        mPaint.setColor(Color.BLACK);                        //设置画笔颜色
        mPaint.setTextSize((float) 30.0);                    //设置字体大小

        canvas.clipRect(100, 100, 350, 600);                //设置显示范围
        canvas.drawColor(Color.WHITE);                      //白色背景
        canvas.drawText("Hello Android!", 150, 300, mPaint); //绘制字符串
    }


}
