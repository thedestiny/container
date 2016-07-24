package com.it.service;

/**
 * Created by xieyue on 2016/7/22.
 * UserServiceTest
 */


import com.it.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {
    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;


    @Test
    public void testSaveUser() {

        User user = new User(101,"jim","China","teacher");
        userService.saveUser(user);
    }

    @Test
    public void testFindUser(){
        User user = userService.findUserById1(10);
        logger.debug("user is {}",user);
    }
}
