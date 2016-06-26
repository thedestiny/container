package com.it.dao;

import com.it.entity.Issue;
import com.it.utils.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by xieyue on 2016/6/25.
 * IssueDao
 */
public class IssueDao {


    public Integer insertQue(String username, String question, String time) {
        System.out.println(username + " : " + question + " : " + time);
        String sql = " insert into issue ( questioner, question , time ) values ( ? , ? , ? )";
        return Dbhelp.update(sql, username, question, time);
    }

    public Integer updateQue() {

        return 1;
    }

    public Integer updateAnswer(String question) {
        String sql = "update issue set answer = answer + 1 where question = ? ";
        return Dbhelp.update(sql, question);
    }

    public Integer updateLike(String question) {
        String sql = "update issue set like = like + 1 where question = ? ";
        return Dbhelp.update(sql, question);
    }

    public Integer updateRight(String question) {
        String sql = "update issue set right = right + 1 where question = ? ";
        return Dbhelp.update(sql, question);
    }

    public Integer updateSkim(String question) {
        String sql = "update issue set skim = skim + 1 where question = ? ";
        return Dbhelp.update(sql, question);
    }

    public List<Issue> getAllIssue() {
        String sql = "select * from issue limit 0 , 20 ";
        return Dbhelp.query(sql, new BeanListHandler<>(Issue.class));
    }

    public Issue findIssue(String question) {
        String sql = "select * from issue where question = ? ";
        return Dbhelp.query(sql, new BeanHandler<>(Issue.class), question);
    }
}
