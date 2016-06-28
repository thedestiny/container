package com.it.dao;

import com.it.entity.Issue;
import com.it.utils.CacheUtils;
import com.it.utils.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by xieyue on 2016/6/25.
 * IssueDao
 */
public class IssueDao {

    private Logger logger = LoggerFactory.getLogger(IssueDao.class);

    public Integer insertQue(String username, String question, String time) {
        CacheUtils.remove("issueList");
        System.out.println(username + " : " + question + " : " + time);
        String sql = " insert into issue ( questioner, question , time ) values ( ? , ? , ? )";
        return Dbhelp.update(sql, username, question, time);
    }

    public Integer updateQue() {
        CacheUtils.remove("issueList");
        return 1;
    }

    public Integer updateAnswer(String question) {
        CacheUtils.remove("issueList");
        String sql = "update issue set answer = answer + 1 where question = ? ";
        return Dbhelp.update(sql, question);
    }

    public Integer updateLike(String question) {
        CacheUtils.remove("issueList");
        String sql = "update issue set like = like + 1 where question = ? ";
        return Dbhelp.update(sql, question);
    }

    public Integer updateRight(String question) {
        CacheUtils.remove("issueList");
        String sql = "update issue set right = right + 1 where question = ? ";
        return Dbhelp.update(sql, question);
    }

    public Integer updateSkim(String question) {
        CacheUtils.remove("issueList");
        String sql = "update issue set skim = skim + 1 where question = ? ";
        return Dbhelp.update(sql, question);
    }
@SuppressWarnings("unchecked")
    public List<Issue> getAllIssue() {
        List<Issue> issueList;
        Object object = CacheUtils.get("issueList");
        if (object != null) {
            issueList = (List<Issue>) object;
            logger.debug(" select data from cache");
        } else {
            String sql = "select * from issue order by id desc limit 0 , 20 ";
            issueList = Dbhelp.query(sql, new BeanListHandler<>(Issue.class));
            CacheUtils.set("issueList", issueList);
            logger.debug(" select data from database");
        }
        return issueList;
    }

    public Issue findIssue(String question) {
        String sql = "select * from issue where question = ? ";
        return Dbhelp.query(sql, new BeanHandler<>(Issue.class), question);
    }
}
