package com.it.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xieyue on 2016/6/18.
 * This is a tool that providing to write get and set methods,which is to long for us.
 */
public class SmallUtils {
    /**
     * @param object 传入一个对象
     * @param sql    传入要查询的sql语句
     * @return 返回一个Object[], 数组的值与sql中？的值一一对应，不用在Dbhelper中写get方法。
     * 注：sql中表名不能和表中列名重复。
     */
    public static Object[] helpGet(Object object, String sql) {
        Class<?> type = object.getClass();
        Method[] methods = type.getMethods();
        int count = 0;
        // 得到方法名 在sql中查询匹配的字段 加入到map集合中 K为值,V为方法
        Map<String, Method> map = new HashMap<>();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                methodName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                if (sql.contains(methodName)) {
                    map.put(methodName, method);
                }
            }
        }
        Object[] objects = new Object[map.size()];
        // sql拆分为数组
        String[] array = sql.split("[ (),=<>?]");
        for (String value : array) {
            if (map.containsKey(value)) {
                try {
                    objects[count++] = map.get(value).invoke(object);
                } catch (Exception e) {
                    throw new RuntimeException("method.invoke(?) 抛出异常", e);
                }
            }
        } 
		if(count == objects.length ){
			return objects;
		}
        Object[] objects1 = new Object[count];
        int n = 0;
        for (Object obj : objects) {
            if (obj != null) {
                objects1[n++] = obj;
            }
        }

//        String regex = "(?<=[(, ])\\s*?\\w+\\s*?(?=[,)=])";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(sql);
//        while (matcher.find()) {
//            // System.out.println("第" + " " + "个匹配" + matcher.group());
//            String value = matcher.group().trim();
//            if (map.containsKey(value)) {
//                try {
//                    objects[count++] = map.get(value).invoke(object);
//                } catch (Exception e) {
//                    throw new RuntimeException("method.invoke(?) 抛出异常", e);
//                }
//            }
//        }

        return objects1;
    }

    public static String getFilename(Part part) {
        String content = part.getHeader("Content-Disposition");
        System.out.println("content is : " + content);
        // 获取最后一个引号
        int n = content.lastIndexOf("\"");
        // 获取最后一个等号
        int m = content.lastIndexOf("=");
        String[] con = content.split("\"");
        for (int i = 0; i < con.length; i++) {
            System.out.println(i + "  " + con[i]);
        }
        System.out.println(con[con.length - 2]);
        return content.substring(m + 2, n);

    }


    public static String getTime() {
        return new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    public static String getTime(int hours) {
        return new DateTime().plusHours(hours).toString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     *
     * @param time1 第一个时间
     * @param time2 第二个时间
     * @return ture 则 time1 > time2 ,否则相反。
     */
    public static boolean compareTime(String time1, String time2) {
        return time1.compareTo(time2) > 0;
    }

    public static String getStartOfDay(){
       return new DateTime().millisOfDay().withMinimumValue().toLocalDate().toString("MM-dd HH:mm:ss:SSS");

    }
    // toLocalDate() 是获取当地时间，可以不加
    public static String getEndOfDay(){
        return  new DateTime().millisOfDay().withMaximumValue().toLocalDate().toString("MM-dd HH:mm:ss:SSS");
    }

    public static String getStartOfWeek(){
        return new DateTime().dayOfWeek().withMinimumValue().toLocalDate().toString("MM-dd HH:mm:ss:SSS");
    }
    public static String getEndOfWeek(){
        return new DateTime().dayOfWeek().withMaximumValue().toLocalDate().toString("MM-dd HH:mm:ss:SSS");
    }

    public static String transtoISO(String str){
        if(StringUtils.isNotEmpty(str)){
            try {
                return new String(str.getBytes("utf-8"),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("转码异常",e);
            }
        }
        return "";
    }

    public static String transtoUTF8(String str){
        if(StringUtils.isNotEmpty(str)){
            try {
                return new String(str.getBytes("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("转码异常",e);
            }
        }
        return "";
    }

    public static String getRemoteIp(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1":ip;
    }

    /**
     *
     * @param str 输入中文
     * @return 返回汉语拼音
     */
    public static String transToPinyin(String str)  {

        HanyuPinyinOutputFormat  format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不要音调
        format.setVCharType(HanyuPinyinVCharType.WITH_V); //使用V
        try {
            return PinyinHelper.toHanYuPinyinString(str,format,"",true);
        } catch (BadHanyuPinyinOutputFormatCombination ex) {
            ex.printStackTrace();
            throw new RuntimeException("转换拼音异常",ex);
        }
    }

    /**
     * 将时间转换为相对时间
     * @param time 时间字符串 exp : 2016-06-03 15:23:26
     * @return 相对时间
     * 1.一年之前的输出格式化时间
     * 2.月份输出范围： 11 个月前-2个月前 以及上个月
     * 3.天数输出范围：半个月前/一周前/6天前-3天前/前天/昨天
     * 4.小时输出范围：23小时-1小时前/半小时前
     * 5.分钟输出范围：29分钟前-1分钟前以及刚刚
     */
    public static String transTime(String time) {

        String[] array = time.split("[- :]");
        int[] arr = new int[7];
        for (int i = 0; i < array.length; i++) {
            arr[i] = Integer.parseInt(array[i]);
        }
        DateTime obj = new DateTime(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
        if (obj.plusMonths(1).isBeforeNow() && obj.plusMonths(12).isAfterNow()) {
            for (int i = 1; i <= 12; i++) {
                if (obj.plusMonths(i).isAfterNow()) {
                    return i> 1 ? i + "个月前":"上个月";
                }
            }
        } else if (obj.plusDays(15).isBeforeNow() && obj.plusDays(31).isAfterNow()) {
            return "半月前";
        } else if (obj.plusDays(7).isBeforeNow() && obj.plusDays(15).isAfterNow()) {
            return "一周前";
        } else if (obj.plusDays(1).isBeforeNow() && obj.plusDays(7).isAfterNow()) {
            for (int i = 1; i <= 7; i++) {
                if (obj.plusDays(i).isAfterNow()) {
                    return i > 2 ? i + "天前" : (i > 1 ? "前天" : "昨天");
                }
            }
        } else if (obj.plusHours(1).isBeforeNow() && obj.plusHours(24).isAfterNow()) {
            for (int i = 1; i <= 24; i++) {
                if (obj.plusHours(i).isAfterNow()) {
                    return i + "小时前";
                }
            }
        } else if (obj.plusMinutes(30).isBeforeNow() && obj.plusMinutes(60).isAfterNow()) {
            return "半小时前";

        } else if (obj.plusMinutes(1).isBeforeNow() && obj.plusMinutes(30).isAfterNow()) {
            for (int i = 1; i <= 30; i++) {
                if (obj.plusMinutes(i).isAfterNow()) {
                    return i + "分钟前";
                }
            }
        } else if (obj.plusMinutes(1).isAfterNow()){
            return "刚刚";
        }
        return time;
    }





}
