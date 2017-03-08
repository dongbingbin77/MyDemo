package com.example.bingbindong.myapplication.utils;

import android.app.ActivityManager;

import android.content.ComponentName;
import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Resources;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明 常用工具类
 */
public class Util {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 图形自适应屏幕，高度适配。注意，此方法仅适用于图形外面是线性布局
     *
     * @param image          原图
     * @param phoneWidth     手机宽度
     * @param phoneHeight    手机高度
     * @param imageWidth     原图宽度
     * @param imageHeight    原图高度
     * @param maxImageHeight 最高高度，变化后的图形的高度不得高于此高度
     * @@return 更改后尺寸的图形
     */
    public static ImageView imageResizeforHeight(ImageView image, int phoneWidth, int phoneHeight, int imageWidth, int imageHeight, int maxImageHeight) {
        ViewGroup.LayoutParams para;
        para = image.getLayoutParams();
        para.width = phoneWidth;
        try {
            para.height = (int) (para.width / (imageWidth / Double.valueOf(imageHeight)));
        } catch (Exception e) {
            e.printStackTrace();
            para.height = imageHeight;
        }
        if (para.height > maxImageHeight) {
            para.height = maxImageHeight;
        }
        image.setLayoutParams(para);
        return image;
    }

    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    /**
     * sdk 9 android 2.3
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * sdk 11 android 3.0
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * sdk 12 android 3.1
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * sdk 16 android 4.1
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * sdk 17 android 4.2
     */
    public static boolean hasJellyBean_MR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    /**
     * sdk 18 android 4.3
     */
    public static boolean hasJellyBean_MR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    /**
     * sdk 19 android 4.4
     */
    public static boolean hasKitkat() {
        return Build.VERSION.SDK_INT >= 19;
    }

    /**
     * sdk 21 android 5.0
     */
    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * 返回格式化后的日期字符串
     *
     * @param date GMT时间,从1970.1.1开始的微秒
     */
    public static String getFormatDateString(long date) {
        return getFormatDateString("yyyy/MM/dd aa HH:mm:ss", date);
    }

    /**
     * 自定义格式化后的日期字符串
     */
    public static String getFormatDateString(String formatstr, long time) {
        SimpleDateFormat format = new SimpleDateFormat(formatstr);
        Date d = new Date(time);
        return format.format(d);
    }

    public static void copyDataBase(Context context, boolean isForceUpdate, String dbName)
            throws Exception {
        String dataBasePath = getDataBaseDir(context);
        String dataBaseFileName = dataBasePath + dbName;
        if (!isForceUpdate && isFileExist(dataBaseFileName)) {
            return;
        }
        File dir = new File(dataBasePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(dataBaseFileName);
        InputStream is = context.getAssets().open(dbName);
        byte[] buffer = new byte[8192];
        int count = 0;
        try {
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
                fos.flush();
            }
        } catch (IOException e) {

        }
        try {
            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isFileExist(String fileName) {
        try {
            File dbFile = new File(fileName);
            return dbFile.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String getDataBaseDir(Context context) {
        return Environment.getDataDirectory() + "/data/"
                + context.getPackageName() + "/databases/";
    }

    public static String getSDCardDir(Context context) {
        try {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return getAppFileCacheDir(context);
        }
    }

    /**
     * 获取应用缓存根目录名，包名最后一个string
     *
     * @param context
     * @return
     */
    public static String getAppCacheRootName(Context context) {
        return context.getPackageName().substring(context.getPackageName().lastIndexOf(".") + 1
                , context.getPackageName().length());
    }

    public static String getAppCacheRootDir(Context context) {
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = Environment.getExternalStorageDirectory();
            path = sd.getPath() + "/"
                    + context.getPackageName().substring(context.getPackageName().lastIndexOf(".") + 1
                    , context.getPackageName().length());
            if (!isFileExist(path)) {
                new File(path).mkdirs();
            }
        }
        return path;

    }

    public static String getImageCacheDir(Context context) {
        String path = getAppFileCacheDir(context) + "images";
        if (!isFileExist(path)) {
            new File(path).mkdirs();
        }
        return path;
    }

    public static String getAppFileCacheDir(Context context) {
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = Environment.getExternalStorageDirectory();
            path = sd.getPath() + "/"
                    + context.getPackageName().substring(context.getPackageName().lastIndexOf(".") + 1, context.getPackageName().length())
                    + "/user/file/";
            if (!isFileExist(path)) {
                new File(path).mkdirs();
            }
        }
        return path;

    }

    public static String getCardFilePath(Context context, String card) {
        return getAppFileCacheDir(context) + card;
    }

    public static String getJJMallFilePath(Context context) {
        return getAppFileCacheDir(context) + "jjmall";
    }

    public static void runTask(AsyncTask<String, ?, ?> taskObj) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            taskObj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        } else {
            taskObj.execute();
        }
    }


    public static boolean checkVersion(String serverVersionCode, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            System.out.println("packageInfo.versionCode=" + packageInfo.versionName + " serverVersionCode=" + serverVersionCode);

            if (packageInfo.versionCode < Integer.parseInt(serverVersionCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkMinVersion(String serverMinVersionCode, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo.versionCode < Integer.parseInt(serverMinVersionCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static int translateStringToInt(String versionCode) {
        String pureStr = versionCode.replaceAll("[\\D]+", "");
        return Integer.parseInt(pureStr);
    }



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }



    public static String formatCardNumber(String cardNo) {
        String cardformat = "";
        if (cardNo == null || cardNo.length() == 0) {
            return cardformat;
        }
        for (int i = 1; i <= cardNo.length(); i++) {
            cardformat += cardNo.charAt(i - 1);
            if (i % 4 == 0) {
                cardformat += " ";
            }
        }
        return cardformat;
    }
    /**pan.wu 20150505 add start**/
    /**
     * URL检查
     *
     * @param pInput 要检查的字符串
     * @return boolean   返回检查结果
     */
    public static boolean isUrl(String pInput) {
        if (pInput == null) {
            return false;
        }
        String regEx = "^(http|https|ftp)//://([a-zA-Z0-9//.//-]+(//:[a-zA-"
                + "Z0-9//.&%//$//-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
                + "2}|[1-9]{1}[0-9]{1}|[1-9])//.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
                + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)//.(25[0-5]|2[0-4][0-9]|"
                + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)//.(25[0-5]|2[0-"
                + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
                + "-9//-]+//.)*[a-zA-Z0-9//-]+//.[a-zA-Z]{2,4})(//:[0-9]+)?(/"
                + "[^/][a-zA-Z0-9//.//,//?//'///////+&%//$//=~_//-@]*)*$";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(pInput);
        return matcher.matches();
    }


    /**
     * gson解析普通的json，返回bean对象
     *
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T normalJson2Bean(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     *
     * @param context
     * @return
     */
    public static boolean isApplicationRunningFront(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i("前台", appProcess.processName);
                    return true;
                } else {
                    Log.i("后台", appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                System.out.println("test：都城在后台运行");
                return true;
            }
        }
        System.out.println("test：都城在前台运行");
        return false;

    }


    /**
     * pan.wu 20150505 add end
     **/
    public static String priceFormat(Double srcprice) throws Exception {
        String result = String.format("%.2f", srcprice);
        if (result.endsWith(".00")) {
            return result.replaceAll("\\.00", "");
        } else {
            return result;
        }
    }


    public static void call(Context context, String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String formatDistance(int meters) {
        if (meters > 1000) {
            return ((int) (meters / 100f)) / 10f + "km";
        }
        return meters + "m";
    }

}