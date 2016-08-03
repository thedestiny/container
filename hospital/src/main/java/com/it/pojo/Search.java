package com.it.pojo;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.it.util.SmallUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class Search {

    private String type;
    private String property;
    private Object object;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static List<Search> getQueryParamList(HttpServletRequest request) {
        List<Search> searchList = Lists.newArrayList();
        Map<String,Object> map = getParams(request);
        for(Map.Entry<String,Object> entry : map.entrySet()){
            String str = entry.getKey();
            Object value = entry.getValue();
            String name = "administrator";
            // q_i_like_title
            if (str.startsWith("q_")) {
                String[] array = str.split("_",4);
                System.out.println("==============================================");
                System.out.println(array[0]);
                System.out.println(array[1]);
                System.out.println(array[2]);
                System.out.println(array[3]);
                if(StringUtils.isNotEmpty(value.toString())){
                    System.out.println(value);
                    Search search = new Search();
                    search.setType(array[2]);
                    search.setProperty(array[3]);
                    value = transValue(value.toString(), array[1]);
                    search.setObject(value);
                    searchList.add(search);
                    request.setAttribute(str,value);
                }
            }
        }
        return searchList;
    }

    private static Object transValue(String value, String valueType) {

        if("s".equalsIgnoreCase(valueType)){
           return value;
        } if("i".equalsIgnoreCase(valueType)){
            return Integer.parseInt(value);
        } if("f".equalsIgnoreCase(valueType)){
            return Float.valueOf(value);
        } if("d".equalsIgnoreCase(valueType)){
            return Double.valueOf(value);
        } if("b".equalsIgnoreCase(valueType)){
            return Boolean.valueOf(value);
        }
        return null;
    }

    public static Map<String,Object> getParams(HttpServletRequest request){
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,Object> map = Maps.newHashMap();
        while (enumeration.hasMoreElements()) {
            String str = enumeration.nextElement();
            String value = request.getParameter(str);
//            if(!value.matches("[\u4e00-\u9fa5]+")){
//                value = SmallUtils.transtoUTF8(value);
//            }
            System.out.println(value);
            map.put(str,value);
        }
        return map;
    }
}
