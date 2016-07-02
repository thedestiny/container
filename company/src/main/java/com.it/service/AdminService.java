package com.it.service;

/**
 * Created by xieyue on 2016/7/2.
 */




import com.it.mapper.AdminMapper;
import com.it.pojo.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class AdminService {
    Logger logger = LoggerFactory.getLogger(AdminService.class);


    private AdminMapper adminMapper;

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public void insertAdmin(){
        logger.debug("this is AdminService insertAdmin");
        Admin admin = new Admin();
        admin.setEmail("1830683298@qq.com");
        admin.setAccount("jim");
        adminMapper.insertAdmin(admin);
        adminMapper.updateAdmin(admin);

    }





}
