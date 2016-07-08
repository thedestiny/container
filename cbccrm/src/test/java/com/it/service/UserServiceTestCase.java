package com.it.service;

/**
 * Created by xieyue on 2016/7/8.
 */


import com.it.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTestCase {
    Logger logger = LoggerFactory.getLogger(UserServiceTestCase.class);


    @Inject
    private UserService userService;

    @Test
    public void findUserTest(){
        User user = userService.findUser(new User("queen"));
        logger.debug("user is {}",user);
        logger.debug("rolename is {}",user.getRole().getRolename());

    }






}
