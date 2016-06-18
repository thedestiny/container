package com.qteng.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieyue on 2016/6/18.
 * This is a tool that providing to write get and set methods,which is to long for us.
 */
public class Utilssmall {

    /**
     * @param object 传入一个对象
     * @param sql    传入要查询的sql语句
     * @return 返回一个Object[], 数组的值与sql中？的值一一对应，不用在Dbhelper中写get方法。
     * example:
     *      public void insertDocument(Document document) {
     *           String sql = "INSERT INTO document (filename,savename,size,displaysize,md5,filetype)VALUE (?,?,?,?,?,?)";
     *           这是之前的写法
     *           Dbhelper.update(sql, document.getFilename(), document.getSavename(), document.getSize(),
     *           document.getDisplaysize(), document.getMd5(), document.getFiletype());
     *           这是现在的写法
     *           Dbhelper.update(sql,Utilssmall.helpGet(document,sql));
     *      }
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
        String[] array = sql.split("[ (),=]");
        for (String value : array) {
            if (map.containsKey(value)) {
                try {
                    objects[count++] = map.get(value).invoke(object);
                } catch (Exception e) {
                    throw new RuntimeException("method.invoke(?) 抛出异常", e);
                }
            }
        }
        return objects;
    }

    /**
     * 获取表单元素的值，用于注册时比较方便
     * @param request  传入request
     * @param parameters 按照顺序放入表单元素name对应的值
     * @return 返回value数组
     */
    public static String[] getForms(HttpServletRequest request, Object... parameters) {
        int len = parameters.length;
        String[] value = new String[len];
        int i = 0;
        for (Object name : parameters) {
            value[i++]=request.getParameter(name.toString());
        }
        return value;
    }



}
