package com.qteng.service;

import com.qteng.mapper.UserDao;
import com.qteng.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by xieyue on 2016/6/30.
 * UserService
 */


public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper=userMapper;
    }

    public UserService() {
    }

    public void setUserDaox(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void sayHai() {
       logger.debug("UserService sayHai ");
        userMapper.sayHello();
    }


}
