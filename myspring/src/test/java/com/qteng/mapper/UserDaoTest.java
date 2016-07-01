package com.qteng.mapper;

/**
 * Created by xieyue on 2016/6/30.
 * UserDaoTest
 */


import com.qteng.pojo.User;
import com.qteng.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoTest {

    @Test
    public void testSayHello() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
//        UserDao userDao = (UserDao) context.getBean("userDao");
//        userDao.sayHello();
        System.out.println("==============================");
        UserService userService = (UserService) context.getBean("userService");
        userService.sayHai();
        System.out.println("==============================");
        User user = (User) context.getBean("user");
        System.out.println(user.getProperties().toString());




    }
}
