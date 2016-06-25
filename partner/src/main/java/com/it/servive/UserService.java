package com.it.servive;

import com.it.dao.UserDao;
import com.it.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/25.
 * UserService
 */
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao = new UserDao();

    // 填写个人详细信息
    public Integer detailInfo(User user){
        return userDao.insertDetail(user);
    }

    // 修改个人详细信息
    public Integer updateInfo(User user){
        return userDao.updateInfo(user);

    }



}
