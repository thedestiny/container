package com.it.service;

/**
 * Created by xieyue on 2016/7/8.
 */


import com.google.common.collect.Maps;
import com.it.mapper.LoginMapper;
import com.it.pojo.Login;
import com.it.pojo.User;
import com.it.utils.SmallUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTestCase {
    Logger logger = LoggerFactory.getLogger(UserServiceTestCase.class);


    @Inject
    private UserService userService;

    @Inject
    private LoginMapper loginMapper;




    @Test
    public void queryCountTest(){

        Map<String, Object> map = Maps.newHashMap();
        map.put("login", new Login(1));
        long num = loginMapper.queryCount(map);
        logger.debug("num is {}",num);
        Assert.assertNotNull(num);

    }

    @Test
    public void getTimetest(){
        String time = SmallUtils.getTime();
        logger.debug("time is {}",time);
    }




}
