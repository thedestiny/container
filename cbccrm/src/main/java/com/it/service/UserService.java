package com.it.service;

/**
 * Created by xieyue on 2016/7/8.
 * UserService
 */


import com.it.mapper.LoginMapper;
import com.it.mapper.UserMapper;
import com.it.pojo.Login;
import com.it.pojo.User;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserMapper userMapper;

    @Inject
    private LoginMapper loginMapper;

    public User findUserByUsername(String username){
        return userMapper.queryUser(new User(username));
    }


    public Integer insertLoginLog(String loginip){
        String logintime = SmallUtils.getTime();



        return loginMapper.insertLoginLog(new Login());

    }







}
