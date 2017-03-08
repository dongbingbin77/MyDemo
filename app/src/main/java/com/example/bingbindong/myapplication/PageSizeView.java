package com.example.bingbindong.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;

import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import com.example.bingbindong.myapplication.utils.Util;

public class PageSizeView extends View {


    private String pageSize="0";

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public PageSizeView(Context context)
    {
        super(context);
        //setWillNotDraw(false);
    }


    public PageSizeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public PageSizeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        setBackgroundColor(Color.TRANSPARENT);
        //canvas.clipRect(0, 0, 170, 170, Region.Op.XOR);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        Path path = new Path();
        path.addArc(new RectF(0, 0, Util.dip2px(this.getContext(),11), Util.dip2px(this.getContext(),11)), 0, 360);
        //path.addArc(pathArcRectF, 0, 360);
        canvas.clipPath(path,Region.Op.XOR);

        Paint paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setAlpha(100);
        canvas.drawRoundRect(new RectF(Util.dip2px(this.getContext(),4), 0, Util.dip2px(this.getContext(),33), Util.dip2px(this.getContext(),11)),Util.dip2px(this.getContext(),5),Util.dip2px(this.getContext(),5), paint1);
        //canvas.drawRoundRect(roundRectF,10,10, paint1);

        Paint paint2 = new Paint();
        paint2.setColor(Color.RED);
        paint2.setAlpha(150);
        paint2.setTextSize(Util.dip2px(this.getContext(),8));
        paint2.setTextAlign(Paint.Align.CENTER);
        int ofwordx=Util.dip2px(this.getContext(),17);
        canvas.drawText("of",ofwordx,Util.dip2px(this.getContext(),8),paint2);

        canvas.drawText(pageSize,ofwordx+Util.dip2px(this.getContext(),8),Util.dip2px(this.getContext(),8),paint2);
//
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.TRANSPARENT);
//        //canvas.drawRect(new Rect(0, 0, 570, 320), paint);
//        canvas.drawArc(new RectF(0, 0, 64, 64), 90, -180, true, paint);

//        Paint paint2 = new Paint();
//        paint2.setColor(Color.GREEN);
//        canvas.drawRect(new Rect(0, 100, 570, 320), paint2);
    }
  
}  