package com.it.dao;

import com.it.entity.Admin;
import com.it.utils.Dbhelp;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

/**
 * Created by xieyue on 2016/6/15.
 * AdminDao
 */
public class AdminDao {

    public Admin findByAccount(String name) {
        String sql = "select * from admin where account=?";
        return Dbhelp.query(sql, new BeanHandler<>(Admin.class), name);
    }

    public Admin findById(int id) {
        String sql = "select * from admin where id=?";
        return Dbhelp.query(sql, new BeanHandler<>(Admin.class), id);
    }

    public int update(Admin admin) {
        String sql = "update admin set pwd = ? where account= ?";
        return Dbhelp.update(sql, admin.getPwd(), admin.getAccount());
    }
    public int insert(Admin admin){
        String sql = "insert into admin (account,password,email) values(?,?,?)";
        return Dbhelp.update(sql,admin.getAccount(),admin.getPassword(),admin.getEmail());
    }

    @Test
    public void test() {
        for (int i = 1; i < 10; i++) {
            Admin admin = findById(i);
            if (admin != null) {
                String password = admin.getPassword();
                String alt = "";
                admin.setPwd(DigestUtils.md5Hex(password + alt));
                update(admin);
            }
        }
    }


}
