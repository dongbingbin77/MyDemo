package com.example.bingbindong.myapplication.utils;

/**
 * Created by dongbingbin on 2017/2/8.
 */

public class jniTest {
    static{
        System.loadLibrary("TestJni");
    }
    public native float[] changeFloatFromCpp(float []javaFloat,int javaInt);
}
