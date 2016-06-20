package com.it.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xieyue on 2016/6/20.
 * TempleTest
 */
public class TempleTest {

    @Test
    public void temple() {
        String sql = "insert into table (id,name,value) value(?,?,?) where name = ?";
        String regex = "(?<=[(, ])\\s*?\\w+\\s*?(?=[,)=])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql);
        List<String> array = new ArrayList<>();
        while (matcher.find()) {
            // System.out.println("第" + " " + "个匹配" + matcher.group());
            array.add(matcher.group().trim());
        }
        for (String str : array) {
            System.out.println(str);
        }
    }

}
