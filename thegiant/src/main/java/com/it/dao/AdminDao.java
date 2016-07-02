package com.it.dao;

/**
 * Created by xieyue on 2016/7/1.
 */


import com.it.mapper.AdminMapper;
import com.it.pojo.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;


public class AdminDao {
    Logger logger = LoggerFactory.getLogger(AdminDao.class);

//
//    private JdbcTemplate jdbcTemplate;
//
//
//    public void insertAdmin(Admin admin) {
//        String sql = "insert into admin (account,password,pwd,email) values(?,?,?,?)";
//        jdbcTemplate.update(sql, admin.getAccount(), admin.getPassword(), admin.getPwd(), admin.getEmail());
//    }
}
