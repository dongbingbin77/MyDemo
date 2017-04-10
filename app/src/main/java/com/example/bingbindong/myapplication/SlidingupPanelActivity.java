package com.example.bingbindong.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class SlidingupPanelActivity extends AppCompatActivity  implements View.OnClickListener,SlidingUpPanelLayout.PanelSlideListener {

    private static final String TAG = "SlidingUpPanel";

    private SlidingUpPanelLayout mLayout;
    private TextView headerView;
    private LinearLayout sliding_linear_layout;
    private Button btn;

    private float offset;

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.main_content){
            //mLayout.setPanelHeight(0);
        }
//        if(id == R.id.add) {
//            //添加内容，并find头部内容部分
//            View view = mLayout.loadView(R.layout.pamap_slidingup_content);
//
//            headerView = view.findViewById(R.id.header);
//
//        } else if(id == R.id.pamap_iv_back){
//            //头部返回
//            if (mLayout != null &&
//                    (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
//                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
//            }
//        } else if(id == R.id.mid) {
//            if (mLayout.getAnchorPoint() == 1.0f) {
//                //居中停留
//                mLayout.setAnchorPoint(0.5f);
//                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
//            } else {
//                //取消居中停留
//                mLayout.setAnchorPoint(1.0f);
//                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
//            }
//
//        }
//        else {
//            Toast.makeText(this, "@_" + id , Toast.LENGTH_SHORT).show();
//        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingup_panel);



        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.setPanelSlideListener(this);
        mLayout.setPanelHeight(0);
        //mLayout.setCoveredFadeColor(Color.TRANSPARENT);
        //headerView = (TextView)findViewById(R.id.main_content);
//        headerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //mLayout.setPanelHeight(0);
//                //headerView.invalidate();
//                //mLayout.set
//            }
//        });
        //headerView.setOnClickListener(this);
        //
        //设置监听器，并控制头部的显示及隐藏

    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
        //展开时slideOffset=1.0f; 折叠时slideOffset=0;
        Log.i(TAG, "onPanelSlide, offset " + slideOffset);
        if(slideOffset>0.5f && headerView!=null) {
            if(headerView.getVisibility()!=View.VISIBLE) {
                headerView.setVisibility(View.VISIBLE);
            }
        }else if(slideOffset<=0.5f && headerView!=null){
            if(headerView.getVisibility()==View.VISIBLE) {
                headerView.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onPanelCollapsed(View panel) {

    }

    @Override
    public void onPanelExpanded(View panel) {

    }

    @Override
    public void onPanelAnchored(View panel) {

    }

    @Override
    public void onPanelHidden(View panel) {

    }

}