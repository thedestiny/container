package com.it.utils;

import org.joda.time.DateTime;

/**
 * Created by xieyue on 2016/6/25.
 * TimeUtils
 */
public class TimeUtils {


    // 获取当前时间字符串
    public static String getTime() {
        DateTime dateTime = new DateTime();
        return dateTime.toString("MM-dd HH:mm:ss");
    }

    public static String getTime(Integer hours) {
        DateTime dateTime = new DateTime();
        return dateTime.plusHours(hours).toString("MM-dd HH:mm:ss");
    }

    /**
     *
     * @param time1 time1 字符串
     * @param time2 time2 字符串
     * @return true 为前者大，false为后者大
     */
    public static boolean compareTime(String time1, String time2) {
        return time1.compareTo(time2) > 0;
    }


}
