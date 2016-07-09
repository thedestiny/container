package com.it.service;

/**
 * Created by xieyue on 2016/7/8.
 * UserService
 */


import com.google.common.collect.Maps;
import com.it.mapper.LoginMapper;
import com.it.mapper.UserMapper;
import com.it.pojo.Login;
import com.it.pojo.User;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

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
        logger.debug("logintime is {}",logintime);
        logger.debug("++++++++++++++++++++++++++++++++++++++++++++");
        Integer userid = ShiroUtil.getCurrentUserId();
        return loginMapper.insertLoginLog(new Login(userid,logintime,loginip));
    }

    public List<Login> queryLoginLog( String start ,String length){
        Integer userid = ShiroUtil.getCurrentUserId();
        Map<String,Object> map = Maps.newHashMap();
        map.put("start",start);
        map.put("length",length);
        map.put("login",new Login(userid));
        return loginMapper.queryLoginLog(map);
    }

    public Integer updateUserPWD(String password){
        User user = new User(ShiroUtil.getCurrentUserId());
        user.setPassword(password);
        return userMapper.updateUserInfo(user);
    }

    public long queryCountByUserid() {
        Integer userid = ShiroUtil.getCurrentUserId();
        Map<String, Object> map = Maps.newHashMap();
        map.put("login", new Login(userid));
        return loginMapper.queryCount(map);
    }

}
