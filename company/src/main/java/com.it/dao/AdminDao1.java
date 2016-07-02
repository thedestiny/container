package com.it.dao;

/**
 * Created by xieyue on 2016/7/2.
 */


import com.it.mapper.AdminMapper;
import com.it.pojo.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
public class AdminDao1 implements AdminMapper {
    Logger logger = LoggerFactory.getLogger(AdminDao1.class);

    public void insertAdmin(Admin admin) {

        logger.debug("this is come from Admin1 insertAdmin this is {}",admin.getAccount());
    }

    public void updateAdmin(Admin admin) {
        logger.debug("this is come from Admin1 updateAdmin this is {}",admin.getEmail());
    }
}
