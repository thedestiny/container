package com.qteng.mapper;

/**
 * Created by xieyue on 2016/6/30.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao1  {
    Logger logger = LoggerFactory.getLogger(UserDao1.class);



    public String sayHello() {
        System.out.println("this is UserDao1");
        logger.debug("this is UserDao1");
        if (1 == 1) {
            throw new RuntimeException("encounter exception");
        }
        return " 123";
    }
}
