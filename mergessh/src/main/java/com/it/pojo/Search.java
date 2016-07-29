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
        Map<String,String> map = getParams(request);
        for(Map.Entry<String,String> entry : map.entrySet()){
            String str = entry.getKey();
            String value = entry.getValue();
            if (str.startsWith("q_")) {
                String[] array = str.split("_");
                if (array.length != 3) {
                    throw new RuntimeException("字段有误！" + str);
                }
                if(StringUtils.isNotEmpty(value)){
                    System.out.println(value);
                    Search search = new Search();
                    search.setType(array[1]);
                    search.setProperty(array[2]);
                    search.setObject(value);
                    searchList.add(search);
                    request.setAttribute(str,value);
                }

            }
        }
        return searchList;
    }

    public static Map<String,String> getParams(HttpServletRequest request){
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> map = Maps.newHashMap();
        while (enumeration.hasMoreElements()) {
            String str = enumeration.nextElement();
            String value = request.getParameter(str);
            if(!value.matches("[\u4e00-\u9fa5]+")){
                value = SmallUtils.transtoUTF8(value);
            }
            map.put(str,value);
        }
        return map;
    }
}
