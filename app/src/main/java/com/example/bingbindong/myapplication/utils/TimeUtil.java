package com.example.bingbindong.myapplication.utils;


import android.content.Context;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

public class TimeUtil {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    public static Calendar today;
    public static Calendar tomorrow;
    public static Calendar afterTomorrow;

    public static final SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss", Locale.getDefault());

    static {
        updateTime();
    }

    /**
     * 获取当前时间的Unix时间戳
     *
     * @return 当前的Unix时间戳
     */
    public static long getUnixTimeByCalendar() {
        Calendar calendar = Calendar.getInstance();
        // 获取当前时区下日期时间对应的时间戳
        long unixTime = calendar.getTimeInMillis();
        return unixTime;
    }

    /**
     * 格式化日期
     *
     * @param long   unixTime unix时间戳
     * @param String format 格式化字符串
     * @return 日期字符串
     */
    public static String formatUnixTime(long unixTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(unixTime);
    }

    public static void updateTime() {
        today = Calendar.getInstance(Locale.getDefault());
        tomorrow = (Calendar) today.clone();
        tomorrow.add(Calendar.DAY_OF_YEAR, 1);
        afterTomorrow = (Calendar) tomorrow.clone();
        afterTomorrow.add(Calendar.DAY_OF_YEAR, 1);
    }

    public static boolean betweenDates(Date date, Calendar minCal, Calendar maxCal) {
        final Date min = minCal.getTime();
        return (date.equals(min) || date.after(min)) // >= minCal
                && date.before(maxCal.getTime()); // && < maxCal
    }




    public static String getTodayString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(TimeUtil.today.getTime());
    }

    public static String getTomorrowString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(TimeUtil.tomorrow.getTime());
    }

    public static int daysBetween(Date first, Date second) throws Exception {

        Calendar calFirst = Calendar.getInstance(Locale.getDefault());
        calFirst.setTime(first);
        setMidnight(calFirst);
        Calendar calSecond = Calendar.getInstance(Locale.getDefault());
        calSecond.setTime(second);
        setMidnight(calSecond);
        long timeBetween = calSecond.getTimeInMillis() - calFirst.getTimeInMillis();
        long daysBetween = Math.abs(timeBetween) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(daysBetween));
    }

    private static boolean sameDate(Calendar cal, Calendar selectedDate) {
        return cal.get(MONTH) == selectedDate.get(MONTH)
                && cal.get(YEAR) == selectedDate.get(YEAR)
                && cal.get(DAY_OF_MONTH) == selectedDate.get(DAY_OF_MONTH);
    }

    public static boolean isToday(Calendar cal) {
        return sameDate(today, cal);
    }

    public static boolean isToday(Date date) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        return sameDate(today, cal);
    }

    public static boolean isTomorrow(Calendar cal) {
        return sameDate(tomorrow, cal);
    }

    public static boolean isTomorrow(Date date) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        return sameDate(tomorrow, cal);
    }

    public static boolean isDayAfterTomorrow(Calendar cal) {
        return sameDate(afterTomorrow, cal);
    }



    public static boolean isBeforeToday(Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar todayMidnight = (Calendar) today.clone();
            setMidnight(todayMidnight);
            setMidnight(calendar);
            return todayMidnight.getTime().after(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static void setMidnight(Calendar cal) {
        cal.set(HOUR_OF_DAY, 0);
        cal.set(MINUTE, 0);
        cal.set(SECOND, 0);
        cal.set(MILLISECOND, 0);
    }

    public static String nowTime() {
        Date date = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatDate.format(date);
    }


    public static String addSeconds(int mCountDownSeconds) {
        Date time = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.SECOND, mCountDownSeconds);
        Date targetDate = calendar.getTime();
        return Constant.TIME_FORMAT.format(targetDate);

    }

    public static int leftSeconds(String timestr) {
        if (!StringUtil.isValid(timestr)) {
            return -1;
        }
        try {
            Date time = Constant.TIME_FORMAT.parse(timestr);
            int leftsceond = (int) (time.getTime() - new Date().getTime());
            return leftsceond / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }




    /**
     * @param startTime H:m:s
     * @param endTime   H:m:s
     * @return
     */
    public static boolean isInBetweenTime(String startTime, String endTime) {
        try {
            Date start = DATE_FORMAT.parse(startTime);
            Date end = DATE_FORMAT.parse(endTime);
            Date now = DATE_FORMAT.parse(DATE_FORMAT.format(Calendar.getInstance().getTime()));

            return start.before(now) && end.after(now);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 判断是否有效期内的对象
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isValidObj(String startTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String currentTime = df.format(new Date());

        int r1 = compare_date(startTime, currentTime);// r1=1,startTime大于currentTime;r1=-1，startTime小于currentTime；r1=0，相等
        int r2 = compare_date(currentTime, endTime);
        if (r1 == 1 || r2 == 1)
            return false;
        return true;
    }

    /**
     * 两个日期比较
     *
     * @param DATE1 格式 yyyy-MM-dd HH:mm:ss
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2) {
        DATE1 = formatDate(DATE1);
        DATE2 = formatDate(DATE2);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //date1大于date2
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //date1小于date2
                return -1;
            } else {
                //date1等于date2
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -2;
    }

    private static String formatDate(String date1) {
        if (date1.length() == 16) {
            date1 += ":00";
        } else if (date1.length() == 10) {
            date1 += " 00:00:00";
        }
        return date1;
    }
}
