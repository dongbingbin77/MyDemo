package com.example.bingbindong.myapplication.DemoView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import com.example.bingbindong.myapplication.utils.Util;

/**
 * Created by dongbingbin on 2017/2/23.
 */

public class ViewDemo extends View {

    private String pageSize="0";

    ValueAnimator mSuccessAnim;
    private int mSuccessAnimOffset;//动画的offset

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public ViewDemo(Context context)
    {
        super(context);
        //setWillNotDraw(false);
    }


    public ViewDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ViewDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSuccessAnim = ValueAnimator.ofInt(0, 200);
        mSuccessAnim.setDuration(1500);
        mSuccessAnim.setInterpolator(new FastOutLinearInInterpolator());
        mSuccessAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSuccessAnimOffset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mSuccessAnim.start();
//        mSuccessAnim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                isShowSuccessAnim = true;
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                onCaptchaMatchCallback.matchSuccess(SwipeCaptchaView.this);
//                isShowSuccessAnim = false;
//                isMatchMode = false;
//            }
//        });
    }


    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
//        setBackgroundColor(Color.TRANSPARENT);
//        //canvas.clipRect(0, 0, 170, 170, Region.Op.XOR);
//        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
//        Path path = new Path();
//        path.addArc(new RectF(0, 0, Util.dip2px(this.getContext(),11), Util.dip2px(this.getContext(),11)), 0, 360);
//        //path.addArc(pathArcRectF, 0, 360);
//        canvas.clipPath(path, Region.Op.XOR);
//
////        Paint paintP = new Paint();
////        paintP.setColor(Color.RED);
////        canvas.drawPath(path,paintP);
//
//        Paint paint1 = new Paint();
//        paint1.setColor(Color.GREEN);
//        paint1.setAlpha(100);
//        canvas.drawRoundRect(new RectF(Util.dip2px(this.getContext(),4), 0, Util.dip2px(this.getContext(),33), Util.dip2px(this.getContext(),11)),Util.dip2px(this.getContext(),5),Util.dip2px(this.getContext(),5), paint1);
//        //canvas.drawRoundRect(roundRectF,10,10, paint1);
//
//        Paint paint2 = new Paint();
//        paint2.setColor(Color.RED);
//        paint2.setAlpha(150);
//        paint2.setTextSize(Util.dip2px(this.getContext(),8));
//        paint2.setTextAlign(Paint.Align.CENTER);
//        int ofwordx=Util.dip2px(this.getContext(),17);
//        canvas.drawText("of",ofwordx,Util.dip2px(this.getContext(),8),paint2);
//
//        canvas.drawText(pageSize,ofwordx+Util.dip2px(this.getContext(),8),Util.dip2px(this.getContext(),8),paint2);


        Paint paint3 = new Paint();
        paint3.setColor(Color.GREEN);
        //paint3.setStrokeWidth(10);
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(5);
        paint3.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));
        Path path1 = new Path();


        path1.moveTo(100,100);
        path1.lineTo(100,150);
        path1.cubicTo(100,150,150,200,100,300);
        path1.lineTo(300,300);
        path1.lineTo(300,100);
        path1.lineTo(100,100);




        //path1.lineTo(1000, 1000);
        //path1.moveTo(100, 100);
        //path1.cubicTo(100, 500, 300, 100, 600, 500);
//        path1.lineTo(100,500);
//        path1.lineTo(100, 200);
//        path1.lineTo(150, 250);
        canvas.drawPath(path1,paint3);


        Paint paint4 = new Paint();
        paint4.setColor(Color.RED);
        //paint4.setStrokeWidth(10);
        paint4.setAntiAlias(true);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(5);
        paint4.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));
        Path path2 = new Path();

        path2.lineTo(0+mSuccessAnimOffset,0+mSuccessAnimOffset);

        canvas.drawPath(path2,paint4);



    }
}
