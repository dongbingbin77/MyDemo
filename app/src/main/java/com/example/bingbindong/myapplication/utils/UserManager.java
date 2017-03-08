package com.example.bingbindong.myapplication.utils;

import android.content.Context;

/**
 * Created by dongbingbin on 2017/2/24.
 */

public class UserManager {
    private static UserManager instance;

    private Context context;

    private UserManager(Context context){
        this.context = context;
    }

    public static UserManager getInstance(Context context){
        if(instance==null){
            instance = new UserManager(context);
        }
        return instance;
    }
}
