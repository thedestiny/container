package com.it.service;

/**
 * Created by xieyue on 2016/7/21.
 * JedisTestCase
 */


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class JedisTestCase {
    Logger logger = LoggerFactory.getLogger(JedisTestCase.class);

    private Jedis jedis = null;

    @Before
    public void setReady() {
        jedis = new Jedis("192.168.56.1");
    }

    @After
    public void conClose() {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Test
    public void testSet() {

        jedis.set("address", "us");
        String value = jedis.get("address");
        logger.debug("address is {}", value);



    }

    @Test
    public void testIncr() {
        jedis.set("signup", "1");
        String value = jedis.get("signup");
        logger.debug("signup is {}", value);

        jedis.incrBy("signup", 10);
        value = jedis.get("signup");
        logger.debug("signup is {}", value);

        jedis.incrByFloat("signup", 3.4F);
        value = jedis.get("signup");
        logger.debug("signup is {}", value);

    }


}
