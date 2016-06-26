package com.it.test;

import com.google.gson.Gson;
import com.it.entity.User;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyue on 2016/6/25.
 * TestCase
 */
public class TestCase {

    @Test
    public void temple() {
        User user = new User();
        user.setUsername("jim");
        user.setPassword("sadfsdf");
        String json = new Gson().toJson(user);

        System.out.println(json);
        User user1 = new Gson().fromJson(json, User.class);
        System.out.println(user1);

    }

    @Test
    public void operatorFileList() {
        String quepath = "G:/issue/que/";
        File file = new File(quepath + "asd");
        List<String> list = new ArrayList<>();
        list.add("asdf");
        list.add("sdfs");
        try {
            FileUtils.writeLines(file, list, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> list1 = new ArrayList<>();
        try {
            list1 = FileUtils.readLines(file, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String arr : list1) {
            System.out.println(arr);
        }
        System.out.println("================================");
        for (String arr : list) {
            System.out.println(arr);
        }
    }
    @Test
    public void testSplit(){
        String sd = "sdfsadf+++sdfsdfsdf";
        String[] as = sd.split("\\+++");
        System.out.println(as[0]);
        System.out.println(as[1]);


    }



}
