package com.it.dao;


import com.it.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao extends PrimaryDao<User,Integer>{
    Logger logger = LoggerFactory.getLogger(UserDao.class);
}
