package com.example.bingbindong.myapplication.utils;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constant {
    public static final String securityKey = "vadjlr4k3o;qj4io23ug9034uji5rjn34io5u83490u590jvi0";
    public static final String APP_DOWNLOAD_URL = "http://a.app.qq.com/o/simple.jsp?pkgname=com.hotelvp.jjzx.activity";
    
    //***************** H5 Start
    public static String URL_H5_HEAD = "http://m.jinjiang.com/h5/app/";
    public static String URL_H5_HOTEL_DETAIL = URL_H5_HEAD + "productlist/hotelinfo/";
    public static final String URL_H5_MEMBER_REGISTER_URL = "http://m.jinjiang.com/h5/single/memberrule";//注册条款
    public static final String URL_H5_MEMBER_HELP_URL = "http://m.jinjiang.com/h5/single/memberhelp";//个人中心-帮助与支持
    //旅游H5
    public static final String URL_TRAVEL_HOME_PAGE = "https://gateway.jinjiang.com/travelterminal/app/home";
    public static final String URL_TRAVEL_ORDER_DETAIL = "https://gateway.jinjiang.com/travelterminal/app/orderdetails?orderNo=";
    //旅游H5（uat）
//    public static final String URL_TRAVEL_HOME_PAGE = "http://172.24.61.1:30003/travelterminal/app/home";
//    public static final String URL_TRAVEL_ORDER_DETAIL = "http://172.24.61.1:30003/travelterminal/app/orderdetails?orderNo=";
    //***************** H5 End

    //***************** Admin配置key Start
    public static final String ACTION_CONFIG_COUPONS_LIST_PROMOTION = "couponListPromotion";
    public static final String ACTION_CONFIG_STARTUP_IMG = "jjdc_startup_img";
    //***************** Admin配置key End

    //***************** 其他 Start
    public static final String JJMALL = "http://mall.jinjiang.com/m/AuthLoginByApp.aspx";
    public static final int NETWORK_TIMEOUT = 60 * 1000;
    //public static final String APP_ID = CConstant.COM_wxAppId;

    public static final String BROCAST_ACTION_LOCATION_DONE = "jjtravel_brocast_action_location_done";
    public static final String BROCAST_ACTION_USER_LOGIN = "jjtravel_brocast_action_user_login";
    public static final String BROCAST_ACTION_PAY_RESULT = "jjtravel_brocast_action_pay_result";

    public static final String DESCRIPTOR = "com.umeng.share";
    public static final String PERMISSION_PAY_RESULT = "com.jjdc.pay.result";
    public static final String BROCAST_ACTION_IMAGE_LOADED = "jjtravel_image_load_complete";
    public static final String BROCAST_ACTION_ORDER_LIST_LOAD = "jjtravel_image_load_order_list";
    public static final String ACTION_BROCAST_STARTUP_PICTURE = "jjtravel_broadcast_startup_picture";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat DATE_FORMAT_MM_DD = new SimpleDateFormat("MM月dd日", Locale.getDefault());
    public static final SimpleDateFormat DATE_FORMAT_WEEK = new SimpleDateFormat("EE", Locale.getDefault());
    public static final SimpleDateFormat DATE_FORMAT_M_D = new SimpleDateFormat("MM-dd", Locale.getDefault());
    public static final SimpleDateFormat DATE_FORMAT_M_D_2 = new SimpleDateFormat("MM/dd", Locale.getDefault());
    public static final SimpleDateFormat DATE_FORMAT_CHECK_IN = new SimpleDateFormat("yyyy-MM-dd   (EE)", Locale.getDefault());
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final DateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());

    public static final String SHARE_USER_ID = "login_info_share";
    public static final String SHARE_LAST_USER_NAME = "login_info_share_last_login_name";
    public static final String SHARE_USER_GUESTID = "login_info_share_guestid";
    public static final String SHARE_USER_TYPE = "login_user_type";
    public static final String SHARE_ENTERPRISE_NAME = "login_ENTERPRISE_NAME";
    public static final String SHARE_ENTERPRISE_ValidDate = "SHARE_ENTERPRISE_ValidDate";

    public static final String ServicePhone = "4008209999";
    public static final String ServicePhone_HK = "+86-21-32169888";
    public static final String ServicePhone_JJE = "4008209999";
    public static final String ENTERPRISE_Phone = "021-61276353";

    public static final String CARD_SILVER = "card_silver.html";
    public static final String CARD_GOLD = "card_gold.html";
    public static final String CARD_PLATINUM = "card_platinum.html";

    public static final String LAST_SELECT_CITY = "last_select_city";
    public static final String KEY_VIEW_HOTEL = "KEY_VIEW_HOTEL";
    public static String URL_RED_LIST_PATTEN = "jinjiang.com|jj-inn.com|tripadvisor.cn";
    public static String IMEI = "";
    public static String CLIENT_IP = "";

    public static String BRANDS_SHOW_BASE_URL = "http://app.jinjiang.com/brands/";
    public static final String ABOUT_US_URL = "http://static.jinjiang.com/opt/static/jjlx/system/aboutus/aboutMember.html";

    public static final String NEARBY_HOT_SALES_DISTANCE = "3000";


    private static String isShowPlatenoBrands;

    public static void setPlatenoBrands(String platenoBrands) {
        isShowPlatenoBrands = platenoBrands;
    }

    public static boolean isShowPlatenoBrands() {
        return "1".equals(isShowPlatenoBrands);
    }
}
