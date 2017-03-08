package com.example.bingbindong.myapplication.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PreferenceManager {
    private final static String APP_CONFIG = "config";

    public final static String CONF_APP_NEW_INSTALL = "APP_NEW_INSTALL";
    public final static String CONF_IS_USER_GUIDE_SHOW = "APP_IS_USER_GUIDE_SHOW";
    public final static String CONF_APP_DB_INSTALL = "APP_DB_INSTALL";
    public final static String CONF_APP_INSTALL_VERSION = "APP_INSTALL_VERSION";
    private final static String CONF_SEARCH_HISTORY = "CONF_SEARCH_HISTORY";
    private static final int MAX_NUM_SEARCHKEYS = 10;

    public static final String FILE_ACTION_CONFIG = "ACTION_CONFIG";
    public static final String KEY_ACTION_CONFIG = "action_config";

    public static SharedPreferences getSharedPreferences(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferences getSharedPreferences(Context context, String filename) {
        return context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

    public static boolean isNewInstall(Context context) {
        return getSharedPreferences(context).getBoolean(CONF_APP_NEW_INSTALL, true);
    }

    public static boolean isUserGuideShow(Context context) {
        return getSharedPreferences(context).getBoolean(CONF_IS_USER_GUIDE_SHOW, false);
    }

    public static int getInstallVersion(Context context) {
        return getSharedPreferences(context).getInt(CONF_APP_INSTALL_VERSION, 0);
    }

    public static boolean isInitDB(Context context) {
        return getSharedPreferences(context).getBoolean(CONF_APP_DB_INSTALL, false);
    }

    public static void setConfig(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).commit();
    }
    public static void setConfig(Context context, String key, String value) {
        //登录，登出，注册都要刷新userid
//        if (key.equals(Constant.SHARE_USER_ID)) {
//            CBDataUtil.updateUserID(value);
//        }
        getSharedPreferences(context).edit().putString(key, value).commit();
    }
//
//    public static void setConfig(Context context, String key, String value) {
//        //登录，登出，注册都要刷新userid
//        if (key.equals(Constant.SHARE_USER_ID)) {
//            CBDataUtil.updateUserID(value);
//        }
//        getSharedPreferences(context).edit().putString(key, value).commit();
//    }
//
//    public static void setConfig(Context context, String key, int value) {
//        getSharedPreferences(context).edit().putInt(key, value).commit();
//    }
//
//    public static void setActionConfig(Context context, ActionConfig actionConfig) {
//        try {
//            SharedPreferences.Editor editor = getSharedPreferences(context, FILE_ACTION_CONFIG).edit();
//            editor.putString(actionConfig.getActionKey(), new Gson().toJson(actionConfig));
//            editor.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void removeActionConfig(Context context, ActionConfig actionConfig) {
//        SharedPreferences.Editor editor = getSharedPreferences(context, FILE_ACTION_CONFIG).edit();
//        editor.remove(actionConfig.getActionKey());
//        editor.commit();
//    }

    public static String getActionConfig(Context context, String key) {
        return getSharedPreferences(context, FILE_ACTION_CONFIG).getString(key, "");
    }

    public static String getConfig(Context context, String key) {
        return getSharedPreferences(context).getString(key, "");
    }

    public static int getIntConfig(Context context, String key) {
        return getSharedPreferences(context).getInt(key, 0);
    }

    public static boolean getBooleanConfig(Context context, String key) {
        return getSharedPreferences(context).getBoolean(key, false);
    }

    public static void saveSearchHistory(Context context, String searchKeyword) {

        if (searchKeyword != null && searchKeyword.length() > 0) {
            try {
                SharedPreferences sp = getSharedPreferences(context);
                String keywords = sp.getString(CONF_SEARCH_HISTORY, "");
                SharedPreferences.Editor editor = sp.edit();
                if (keywords.length() > 0) {

                    String sb = "";
                    String[] keys = keywords.split(",");
                    List<String> keysList = new ArrayList<>(Arrays.asList(keys));

                    if (keysList.contains(searchKeyword)) {

                        keysList.remove(searchKeyword);
                        keysList.add(searchKeyword);

                        sb = listToString(keysList);
                    } else {

                        if (keysList.size() >= MAX_NUM_SEARCHKEYS) {
                            keysList.remove(0);
                        }
                        sb = listToString(keysList) + "," + searchKeyword;
                    }

                    editor.putString(CONF_SEARCH_HISTORY, sb);

                } else {
                    editor.putString(CONF_SEARCH_HISTORY, searchKeyword);
                }

                editor.apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static List<String> getSearchHistory(Context context) {

        try {
            SharedPreferences sp = getSharedPreferences(context);
            String keywords = sp.getString(CONF_SEARCH_HISTORY, "");
            if (keywords.length() > 0) {
                String[] keys = keywords.split(",");
                List<String> kList = new ArrayList<>(Arrays.asList(keys));
                Collections.reverse(kList);
                return kList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void clearSearchHistory(Context context) {
        try {
            SharedPreferences sp = getSharedPreferences(context);
            sp.edit().putString(CONF_SEARCH_HISTORY, "").apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String listToString(List<String> dataList) {

        if (dataList == null || dataList.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String k : dataList) {
            if (sb.length() > 0) {
                sb.append(",").append(k);
            } else {
                sb.append(k);
            }
        }
        return sb.toString();
    }

}
