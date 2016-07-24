package com.it.service;

/**
 * Created by xieyue on 2016/7/22.
 * JsonTestCase
 */


import com.it.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JsonTestCase {
    Logger logger = LoggerFactory.getLogger(JsonTestCase.class);

    @Autowired
    private RedisTemplate<String,User> redisTemplate;

    @Before
    public void setUp(){
        // 序列化key 和 value
        redisTemplate.setKeySerializer( new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
    }

    @Test
    public void testSet(){
        User user = new User(10,"queen","StartCity","Arrow");
        redisTemplate.opsForValue().set("user:10",user);
    }

    @Test
    public void testGet(){
        User user = redisTemplate.opsForValue().get("user:10");
        logger.debug("user is {}",user);
    }

}
