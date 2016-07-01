package com.qteng.service;

import com.qteng.mapper.UserDao;
import com.qteng.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * Created by xieyue on 2016/6/30.
 * UserService
 */

@Named
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserMapper userMapper;

//    public UserService(UserMapper userMapper) {
//        this.userMapper=userMapper;
//    }
//
//    public UserService() {
//    }
//
//    public void setUserDaox(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    public void sayHai() {
       logger.debug("UserService sayHai ");
        userMapper.sayHello();
    }


}
