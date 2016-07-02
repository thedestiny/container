package com.it.service;

/**
 * Created by xieyue on 2016/7/2.
 */


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class AdminServiceTest {
    Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);

    @Inject
    private AdminService adminService;

    @Test
    public void insertAdminTest(){

        adminService.insertAdmin();


    }
}
