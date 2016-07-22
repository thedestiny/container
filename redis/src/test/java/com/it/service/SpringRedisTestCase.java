package com.it.service;

/**
 * Created by xieyue on 2016/7/21.
 * SpringRedisTestCase
 */


import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class SpringRedisTestCase {
    Logger logger = LoggerFactory.getLogger(SpringRedisTestCase.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testMset(){

        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        Map<String,String> map = Maps.newHashMap();
        map.put("address:1","us");
        map.put("address:2","uk");
        operations.multiSet(map);
        String vale  = operations.get("address:1");
        logger.debug("the value is {}",vale);


    }


}
