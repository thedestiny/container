package com.qteng.test;

import org.joda.time.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/14.
 */
public class TimeTest {

    Logger logger = LoggerFactory.getLogger(EmailTest.class);

    @Test
    public void timeTest() {

        // 显示为当前时间
        DateTime dateTime = new DateTime();
        // 时区 ZZ 星期缩写 E 上下午判断 a ZZZZ地址时区 MMMM 代表月份字母或文字 EEEE 全拼
        System.out.println(dateTime.toString("ZZZZ EEEE yyyy-MMMM-dddd HH:mm:ss:SSSaa"));
        //  (yyyy-MM-ddTHH:mm:ss.SSSZZ) 默认输出格式为 年月日星期 时分秒毫秒 时区
        System.out.println(dateTime.toString());
        System.out.println(dateTime.getDayOfMonth());
        // 指定日期
        DateTime dateTime1 = new DateTime(2010, 2, 1, 10, 12, 20);
        // 加 秒/分/时/日/星期/月/年/.plusDays() .plusHours() .plusWeeks() .plusYears()
        // 减 秒/分/时/日/星期/月/年/ .minusDays()
        // .getDayOfMonth() 获取当前天数在月中的第几天,也是获取日 getDayOfWeek() getDayOfYear()

        System.out.println(dateTime1.plusHours(1).getDayOfMonth());
        System.out.println(dateTime1.minusDays(1));
        System.out.println(dateTime1.toString("Z E yyyy-MM-dd HH:mm:ss:SSS"));
        // LocalDate 年月日 LocalTime 时分秒毫秒
        LocalDate localDate = new LocalDate();
        System.out.println(localDate.toString("yy-M-d"));
        LocalTime localTime = new LocalTime();
        System.out.println(localTime.toString("H:m:s"));
        // 获取月末日期
        DateTime lastday = dateTime.dayOfMonth().withMaximumValue();
        System.out.println(lastday);
        // 获取9天后当周的周一的日期
        DateTime week = dateTime.plusDays(20).dayOfWeek().withMinimumValue();
        System.out.println(week);
        //设置时区
        DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
        DateTime begin = new DateTime("2012-02-01");
        DateTime end = new DateTime("2012-02-04");

        //计算区间毫秒数  getMillis()
        Duration d = new Duration(begin, end);
        long time = d.getMillis();
        System.out.println(d);
        //计算区间天数   时/分/秒/年/月/日均可计算 设置PeriodType的属性  days 计算天数
        Period p = new Period(begin, end, PeriodType.hours());
        int days = p.getHours();
        System.out.println(days);
        //计算特定日期是否在该区间内
        Interval i = new Interval(begin, end);
        boolean contained = i.contains(new DateTime("2012-03-01"));
        // 日期比较
        DateTime d1 = new DateTime("2012-02-01");
        DateTime d2 = new DateTime("2012-05-01");

        //和系统时间比
        boolean b1 = d1.isAfterNow();
        boolean b2 = d1.isBeforeNow();
        boolean b3 = d1.isEqualNow();
        System.out.println(b1 + "  " + b2 + "  " + b3);
        //和其他日期比
        boolean f1 = d1.isAfter(d2);
        boolean f2 = d1.isBefore(d2);
        boolean f3 = d1.isEqual(d2);

    }


}
