package com.example.bingbindong.myapplication;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.bingbindong.myapplication.Adapter.NetworkImageHolderView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ConvenientbannerActivity extends AppCompatActivity {




    final List<Drawable> lst = new ArrayList<Drawable>();

    List<String> imgLst = new ArrayList<>();

    ViewPager mnew_hotel_images_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenientbanner);


        setUI();

        imgLst.add("http://wx4.sinaimg.cn/mw600/006D2KSdly1fbr9bed8y4j30ut18g7wi.jpg");
        imgLst.add("http://wx4.sinaimg.cn/mw600/006D2KSdly1fbqhftftkyj30f00l6net.jpg");
        imgLst.add("http://ww3.sinaimg.cn/mw600/005vbOHfgw1fbson3wihzj30g00sgq6j.jpg");
        imgLst.add("http://ww3.sinaimg.cn/mw600/005vbOHfgw1fbsoncyycjj30ia0q5gvg.jpg");
        imgLst.add("http://wx3.sinaimg.cn/mw600/006D2KSdly1fbr9iezlhzj30v518ge82.jpg");
        imgLst.add("http://wx4.sinaimg.cn/mw600/006D2KSdly1fbr8vbdw60j30zk0yab29.jpg");
        imgLst.add("http://ww4.sinaimg.cn/mw600/005vbOHfgw1fbrlew9xk2j30fk0nfq7b.jpg");
        imgLst.add("http://ww2.sinaimg.cn/mw600/005vbOHfgw1fbrleqt2mrj30u00u0juh.jpg");
        imgLst.add("http://ww3.sinaimg.cn/mw600/005vbOHfgw1fbrlg4o56jj30fo0niq47.jpg");
        imgLst.add("http://ww4.sinaimg.cn/mw600/661eb95cgw1fbri48wqi8j20gg0cf3z5.jpg");
        imgLst.add("http://ww2.sinaimg.cn/mw600/661eb95cgw1fbri48gzwqj20hs0oadi8.jpg");
        imgLst.add("http://ww3.sinaimg.cn/mw600/67175fe4jw1fbmw4myh4mj20qo0hsjuh.jpg");


        LinearLayout indicator = (LinearLayout)findViewById(R.id.homepage_point_indicator);


        try {
            final ConvenientBanner convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
            convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, lst)    //设置需要切换的View
                    .setPointViewVisible(true)    //设置指示器是否可见
                    .setPageIndicator(new int[]{R.drawable.shape_point_page_indicator_normal, R.drawable.shape_point_page_indicator_selected})   //设置指示器圆点
                    .startTurning(5000)     //设置自动切换（同时设置了切换时间间隔）
                    .stopTurning();    //关闭自动切换
            //.setManualPageable(false)  //设置手动影响（设置了该项无法手动切换）
            //.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT) //设置指示器位置（左、中、右）
            //.setOnItemClickListener(this); //设置点击监听事件
            //convenientBanner.setInd

            for (int i = 0; i < imgLst.size(); i++) {

                final ImageView iv = new ImageView(this);
                iv.getResources();
                Picasso.with(this).load(imgLst.get(i)).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(iv, new Callback() {
                    @Override
                    public void onSuccess() {
                        //HomePage.this.activeCommonLayout.setVisibility(View.VISIBLE);
                        lst.add(iv.getDrawable());
                        convenientBanner.notifyDataSetChanged();
                    }

                    @Override
                    public void onError() {
                        //HomePage.this.hideActiveCommonLayout();
                    }
                });
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }



        TextView btn = (TextView)findViewById(R.id.addDyCom);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rl  = (RelativeLayout)findViewById(R.id.activity_convenientbanner);
                TextView tv = new TextView(ConvenientbannerActivity.this);

                tv.setText("动态添加");

                //设置RelativeLayout布局的宽高
                RelativeLayout.LayoutParams relLayoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                relLayoutParams.addRule(RelativeLayout.RIGHT_OF,R.id.addDyCom);
                relLayoutParams.addRule(RelativeLayout.BELOW,R.id.convenientBanner);

                rl.addView(tv,relLayoutParams);



            }
        });


        TextView addDyInflater = (TextView)findViewById(R.id.addDyInflater);

        addDyInflater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rl  = (RelativeLayout)findViewById(R.id.activity_convenientbanner);


                View view = ConvenientbannerActivity.this.getLayoutInflater().inflate(R.layout.activity_inflater_test,null);


                TextView tv22 = (TextView)view.findViewById(R.id.tv22);

                tv22.setBackgroundColor(Color.BLUE);

                //设置RelativeLayout布局的宽高
                RelativeLayout.LayoutParams relLayoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                //relLayoutParams.addRule(RelativeLayout.RIGHT_OF,R.id.addDyCom);
                relLayoutParams.addRule(RelativeLayout.BELOW,R.id.addDyCom);

                rl.addView(view,relLayoutParams);



            }
        });
    }


    private void setUI(){
        mnew_hotel_images_viewpager = (ViewPager)findViewById(R.id.new_hotel_images_viewpager);
        mnew_hotel_images_viewpager.setAdapter(new ViewPagerontentAdapter(getSupportFragmentManager()));
        //mnew_hotel_images_viewpager.setPageTransformer(true, new DepthPageTransformer());
    }


    //    class ViewPagerontentAdapter extends FragmentPagerAdapter {
    class ViewPagerontentAdapter extends FragmentStatePagerAdapter {

        public ViewPagerontentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            Bundle args;

            fragment = new ViewPageContentFragment();
            args = new Bundle();
            args.putInt(ViewPageContentFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

    public static class ViewPageContentFragment extends Fragment {

        public static final String ARG_OBJECT = "object";
        public final String TAG = ViewPageContentFragment.class.getSimpleName();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Toast.makeText(getActivity(), "onCreate", Toast.LENGTH_SHORT).show();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view;
            view = inflater.inflate(R.layout.activity_view_page_content_layout, container, false);

            Bundle args = getArguments();
            ((TextView) view.findViewById(R.id.view_page_content_layout_txtText)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return view;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Toast.makeText(getActivity(), "onDestroy", Toast.LENGTH_SHORT).show();
        }
    }


    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
