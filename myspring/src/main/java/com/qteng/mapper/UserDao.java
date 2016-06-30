package com.qteng.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/30.
 * UserDao
 */


public class UserDao implements UserMapper {

    Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Override
    public String sayHello() {
        System.out.println("hello,word!,comes from UserDao");
//        logger.debug("this is come from logger!");
        if (1 == 1) {
            throw new RuntimeException("encounter exception");
        }
        return "123456";
    }

    public UserDao() {
        System.out.println("UserDao is create");
    }


    public void init(){
        System.out.println("UserDao is start !");
    }

    public void destroy(){
        System.out.println("UserDao is destroy !");
    }




}
