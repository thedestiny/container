package com.it.dao;

/**
 * Created by xieyue on 2016/7/1.
 */


import com.it.mapper.AdminMapper;
import com.it.pojo.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:ApplicationContext.xml")
public class AdminDaoTestCase {
    Logger logger = LoggerFactory.getLogger(AdminDaoTestCase.class);

    @Inject
    private AdminMapper adminMapper;

    @Test
    public void insertAdminTest(){
        Admin admin = new Admin("iron","123456","456123","183@163.com");
        logger.debug("this is AdminDaoTestCase insertAdminTest");
        adminMapper.insertAdmin(admin);

    }


}
