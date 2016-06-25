package com.it.dao;

import com.it.utils.Dbhelp;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/25.
 * IssueDao
 */
public class IssueDao {

    private Logger logger = LoggerFactory.getLogger(IssueDao.class);

    public Integer insertQue(String username, String quemd5, String time) {
        String sql = "insert into issue ( questioner , question , time ) values ( ?, ?, ?)";
        return Dbhelp.update(sql, username, quemd5, time);
    }






}
