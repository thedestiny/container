package com.it.test;

import com.google.gson.Gson;
import com.it.entity.User;
import org.junit.Test;

import java.util.*;

/**
 * Created by xieyue on 2016/6/23.
 * JsonTest
 */
public class JsonTest {

    private User user = new User(1,"jin","ball","math");
    private User user1 = new User(2,"flash","ball","sport");
    private User user2 = new User(3,"arrow","ball","politic");
    private User user3 = new User(4,"queen","ball","science");
    private List<User> list = new ArrayList<>();
    private Map<String,User> map = new HashMap<>();
    private Set<User> set = new HashSet<>();

    @Test
    public void ObjectToJson(){
        Gson gson = new Gson();
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        // 对象数组
        String listtojson = gson.toJson(list);
        System.out.println(listtojson);
        System.out.println("========================");
        set.add(user);
        set.add(user1);
        // 对象数组
        String settojson = new Gson().toJson(set);
        System.out.println(settojson);
        System.out.println("========================");
        map.put("1",user);
        map.put("2",user1);
        map.put("3",user2);
        // 对象
        String maptojson = new Gson().toJson(map);
        System.out.println(maptojson);
        // 数组对应数组，对象对应对象
    }
}
