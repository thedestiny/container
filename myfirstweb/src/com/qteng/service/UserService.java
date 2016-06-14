package com.qteng.service;

import com.qteng.dao.CountDao;
import com.qteng.entity.Count;

/**
 * Created by xieyue on 2016/6/14.
 */
public class UserService {

    private CountDao dao = new CountDao();

    public Count getCount(String user) {

        return dao.login(user);
    }
}
