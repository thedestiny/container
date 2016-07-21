package com.it.service;

/**
 * Created by xieyue on 2016/7/21.
 * SpringRedisTestCase
 */


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SpringRedisTestCase {
    Logger logger = LoggerFactory.getLogger(SpringRedisTestCase.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testMset(){


    }


}
