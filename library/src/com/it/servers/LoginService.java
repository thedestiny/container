package com.it.servers;

import com.it.dao.AdminDao;
import com.it.entity.Admin;

/**
 * Created by xieyue on 2016/6/15.
 * this is LoginService
 */
public class LoginService {
    private AdminDao adminDao = new AdminDao();
    public Admin login(String name) {
          return adminDao.findByAccount(name);
    }

}

