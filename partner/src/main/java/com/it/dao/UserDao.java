package com.it.dao;

import com.it.entity.User;
import com.it.utils.Dbhelp;
import com.it.utils.SmallUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by xieyue on 2016/6/25.
 * UserDao
 */
public class UserDao {

    public Integer insert(User user) {
        String sql = "insert into user ( username, email, password , md5pwd , time) " +
                "VALUES (?,?,?,?,?)";
        return Dbhelp.update(sql, SmallUtils.helpGet(user, sql));
    }

    // 填写个人详细信息
    public Integer insertDetail(User user) {
        String sql = "update user set brith = ?, job =?, address=?, tel=?, resume=?, resume=? where username = ?";
        return Dbhelp.update(sql, SmallUtils.helpGet(user, sql));
    }

    // 更新提问数量
    public Integer updateQue(String username) {
        String sql = "update user set quenum = quenum + 1 where username = ?";
        return Dbhelp.update(sql, username);
    }

    // 更新回答数量
    public Integer updateAns(String username) {
        String sql = "update user set ansnum = ansnum + 1 where username = ?";
        return Dbhelp.update(sql, username);
    }

    // 更新采纳数量
    public Integer updateAcc(String username) {
        String sql = "update user set accept = accept + 1 where username = ?";
        return Dbhelp.update(sql, username);
    }

    // 修改信息
    public Integer updateInfo(User user) {
        String sql = "update user set brith = ?, job =?, address=?, tel=?, resume=?, resume=? , email = ? where username = ?";
        return Dbhelp.update(sql, SmallUtils.helpGet(user, sql));
    }

    // 修改密码
    public Integer updatePwd(User user) {
        String sql = "update user set md5pwd = ? where username = ? ";
        return Dbhelp.update(sql, SmallUtils.helpGet(user, sql));
    }

    public User login(User user) {
        String sql = "select * from user where username = ? and password = ?";
        return Dbhelp.query(sql,new BeanHandler<>(User.class),SmallUtils.helpGet(user,sql));
    }
}
