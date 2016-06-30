package com.it.servive;

import com.it.dao.UserDao;
import com.it.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/26.
 * LoginService
 */
public class LoginService {

    private Logger logger = LoggerFactory.getLogger(LoginService.class);
    private UserDao userDao = new UserDao();

    public User login(User user){
        return userDao.login(user);
    }
}
