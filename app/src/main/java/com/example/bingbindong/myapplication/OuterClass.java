package com.example.bingbindong.myapplication;

/**
 * Created by dongbingbin on 2017/1/13.
 */

public class OuterClass {

    private String Name;

    public class InnerClass{

        public InnerClass(){
            Name="dongbingbin";
            System.out.print(" InnerClass Created ! ");
        }

        public OuterClass GetOuterClassObject(){
            return OuterClass.this;
        }

    }
}
