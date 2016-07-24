package com.it.service;

/**
 * Created by xieyue on 2016/7/22.
 * StringRedisTemplateTestCase
 */


import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.it.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class StringRedisTemplateTestCase {
    Logger logger = LoggerFactory.getLogger(StringRedisTemplateTestCase.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSave(){

        stringRedisTemplate.opsForValue().set("user:1","rose");
    }

    @Test
    public void testSaveSet(){
        stringRedisTemplate.opsForSet().add("user:vote","queen","tom","iron");
        Set<String> setVote = stringRedisTemplate.opsForSet().members("user:vote");
        for(String value : setVote){
            logger.debug("voter is {}",value);
        }
    }

    @Test
    public void testSaveHash(){
        stringRedisTemplate.opsForHash().put("student:1","name","jack");
        Map<String,Object> map = Maps.newHashMap();
        map.put("address","us");
        map.put("age",18);
        stringRedisTemplate.opsForHash().putAll("student:1",map);
        // stringRedisTemplate.opsForHash().
        //todo get hash


    }

    @Test
    public void testSaveUser(){
        User user = new User(103,"flash","CenterCity","Flash");
        stringRedisTemplate.opsForValue().set("user:100",new Gson().toJson(user));

        String json = stringRedisTemplate.opsForValue().get("user:100");
        User user1 = new Gson().fromJson(json,User.class);
        logger.debug("user is {}",user1);
    }






}
