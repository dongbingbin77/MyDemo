package com.example.bingbindong.myapplication.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.bingbindong.myapplication.R;

import cz.msebera.android.httpclient.client.cache.Resource;

public class NetworkImageHolderView implements Holder<Drawable> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context,int position, Drawable data) {
        //imageView.setImageResource(R.drawable.ic_default_adimage);
        imageView.setImageDrawable(data);
    }
}