package com.example.bingbindong.myapplication.utils;

/**
 * Created by bingbin.dong on 2016/12/12.
 */

public enum DaysE {

    Mon(1){
        @Override
        public void Test()
        {

        }
    },Tus(2){
        @Override
        public void Test()
        {

        }
    };

    private int value;

    private DaysE(int value) {
        this.value = value;
    }

    public void Test()
    {

    }
}
