package com.example.bingbindong.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bingbindong.myapplication.Adapter.RecylerViewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecylerViewActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //ArrayList<String> myDataset = new ArrayList<String>();
        String[] myDataset = {"1","2","3","4","5","6"};
        // specify an adapter (see also next example)
        mAdapter = new RecylerViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
