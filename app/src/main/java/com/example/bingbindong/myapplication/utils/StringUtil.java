package com.example.bingbindong.myapplication.utils;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

;

public class StringUtil {
    public static Map<String, String> START_LEVEL_MAP = new HashMap<String, String>();
    public static Map<String, String> ORDER_STATUS_MAP = new HashMap<String, String>();
    public static Map<String, String> ORDER_PAYMENT_STATUS_MAP = new HashMap<String, String>();
    public static Map<String, String> MEMBER_LEVEL_MAP = new HashMap<String, String>();

    /**
     * 判断字符串是否有效 (不为 null 且 长度不为0)
     *
     * @return
     */
    public static boolean isValid(CharSequence str) {
        //有时候接口会返回字符串 “null”
        return str != null && str.length() > 0 && !"null".equalsIgnoreCase(str.toString());
    }

    public static String formatCityName(String srcCity) {
        try {
            if (srcCity.endsWith("市")) {
                return srcCity.substring(0, srcCity.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srcCity;
    }

    public static String inputStreamTOString(InputStream in, String encoding) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        return new String(outStream.toByteArray(), encoding);
    }


    static {
        START_LEVEL_MAP.put("ONE", "一星级");
        START_LEVEL_MAP.put("TWO", "二星级");
        START_LEVEL_MAP.put("THREE", "三星级");
        START_LEVEL_MAP.put("FOUR", "四星级");
        START_LEVEL_MAP.put("FIVE", "五星级");

        ORDER_STATUS_MAP.put("CONFIRM", "已确认");
        ORDER_STATUS_MAP.put("UNPAY", "待支付");
        ORDER_STATUS_MAP.put("PAY_SUCCESS", "支付成功");
        ORDER_STATUS_MAP.put("PAY_FAILURE", "待支付");
        ORDER_STATUS_MAP.put("REFUND_ALREADY", "退款中");
        ORDER_STATUS_MAP.put("REFUND_SUCCESS", "退款成功");
        ORDER_STATUS_MAP.put("CANCEL", "已取消");
        ORDER_STATUS_MAP.put("FAIL", "预订失败");


        ORDER_PAYMENT_STATUS_MAP.put("PAY_WAITING", "待支付");
        ORDER_PAYMENT_STATUS_MAP.put("PAY_SUCCESS", "支付成功");
        ORDER_PAYMENT_STATUS_MAP.put("PAY_FAILURE", "待支付");
        ORDER_PAYMENT_STATUS_MAP.put("REFUNDING", "退款中");
        ORDER_PAYMENT_STATUS_MAP.put("CHARGE_SUCCESS", "退款成功");
        ORDER_PAYMENT_STATUS_MAP.put("REFUND_SUCCESS", "退款成功");

        MEMBER_LEVEL_MAP.put("11", "银卡");
        MEMBER_LEVEL_MAP.put("12", "金卡");
        MEMBER_LEVEL_MAP.put("13", "白金卡");
        MEMBER_LEVEL_MAP.put("14", "黑卡");
    }

    /**
     * BASE64 加密
     *
     * @param str
     * @return
     */
    public static String encryptBASE64(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            byte[] encode = str.getBytes("UTF-8");
            // base64 加密
            return new String(Base64.encode(encode, 0, encode.length, Base64.DEFAULT), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * BASE64 解密
     *
     * @param str
     * @return
     */
    public static String decryptBASE64(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            byte[] encode = str.getBytes("GBK");
            // base64 解密
            return new String(Base64.decode(encode, 0, encode.length, Base64.DEFAULT), "GBK");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
