package com.example.bingbindong.myapplication.utils;

import android.app.Activity;
import android.os.CountDownTimer;



/**
 * Created by paulliu on 15/3/27.
 */
public class CountDownNumUtil {
    private Activity mActivity;
    private String lastSuccessTime;
    private int timeCountDown = 0;
    private String countTime = "";
    private int mCountDownSeconds;
    private CountDownTimer countDownTimer;
    private CountDownListener countDownListener;
    private int leftSeconds = 0;

    /**
     * @param activity
     * @param activityName
     * @param countDownSeconds
     */
    public CountDownNumUtil(Activity activity, String activityName, int countDownSeconds) {
        mActivity = activity;
        countTime = activityName;
        mCountDownSeconds = countDownSeconds;
        initCountDownTime();
    }


    private String lastTime() {
        return PreferenceManager.getConfig(mActivity, countTime);
    }

    public boolean isInTime() {
        lastSuccessTime = lastTime();
        timeCountDown = TimeUtil.leftSeconds(lastSuccessTime);
        if (timeCountDown < 0) {
            timeCountDown = mCountDownSeconds;
            return false;
        } else {
            return true;
        }
    }

    private void initCountDownTime() {
        lastSuccessTime = lastTime();
        if (!StringUtil.isValid(lastSuccessTime)) {
            lastSuccessTime = TimeUtil.addSeconds(mCountDownSeconds);
        }
        isInTime();
    }


    public interface CountDownListener {
        void countDownTickListener(int countDown);

        void countDownFinishListenter();
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        this.countDownListener = countDownListener;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public void startCountdown() {

        if (timeCountDown > 0 && timeCountDown <= mCountDownSeconds) {
            countDownTimer = new CountDownTimer(timeCountDown * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    leftSeconds = (int) millisUntilFinished / 1000;
                    if (countDownListener != null) {
                        countDownListener.countDownTickListener(leftSeconds);
                    }
                }

                @Override
                public void onFinish() {
                    if (countDownListener != null) {
                        countDownListener.countDownFinishListenter();
                    }
                    stopCountdown();
                }

            };
            countDownTimer.start();
        }

    }

    public void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            PreferenceManager.setConfig(mActivity, countTime, TimeUtil.addSeconds(leftSeconds));
        }
    }


}
