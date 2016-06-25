package com.it.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Part;
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
                    System.out.println(count + " : " + map.get(value).invoke(object).toString());
                    objects[count++] = map.get(value).invoke(object);

                } catch (Exception e) {
                    throw new RuntimeException("method.invoke(?) 抛出异常", e);
                }
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
        Object[] objects1 = new Object[count];
        int n =0;
        for (Object obj: objects) {
            if(obj != null){
                objects1[n++] = obj;
            }
        }
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
}
