package com.it.dao;

/**
 * Created by xieyue on 2016/7/2.
 */


import com.it.mapper.AdminMapper;
import com.it.pojo.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminDao implements AdminMapper {
    Logger logger = LoggerFactory.getLogger(AdminDao.class);

    public void insertAdmin(Admin admin) {

        logger.debug("insertAdmin this is {}",admin.getAccount());
    }

    public void updateAdmin(Admin admin) {
        logger.debug("updateAdmin this is {}",admin.getEmail());
    }
}
