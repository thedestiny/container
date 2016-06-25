package com.it.dao;

import com.it.entity.Register;
import com.it.utils.Dbhelp;
import com.it.utils.SmallUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/25.
 * RegisterDao
 */
public class RegisterDao {
    private Logger logger = LoggerFactory.getLogger(RegisterDao.class);

    /**
     * @param register 传进一个register 对象
     * @return 返回影响的行数
     */
    public Integer Insert(Register register) {
        String sql = "insert into register ( username, email, password, identify, time) " +
                "VALUES (?,?,?,?,?)";
        System.out.println(register.toString());
        return Dbhelp.update(sql, SmallUtils.helpGet(register, sql));
//        return Dbhelp.update(sql, register.getUsername(), register.getEmail(), register.getPassword(), register.getIdentify()
//                , register.getTime());
    }

    /**
     * @param username 传入一个username
     * @return 返回 Register对象
     */
    public Register queryUsername(String username) {
        String sql = "select * from register where username= ?";
        return Dbhelp.query(sql, new BeanHandler<>(Register.class), username);
    }

    @Test
    public void testsmall() {
        Register register = new Register("xieyue", "xieyue86@163.com", "asdfasdf");
        register.setTime("2015-2-26");
        register.setIdentify("asdfasdgdfgdsf");
        String sql = "insert into register ( username, email, password, identify, time) " +
                "VALUES (?,?,?,?,?)";
        Object[] array = SmallUtils.helpGet(register, sql);
        System.out.println("length is :" + array.length);
        for (Object obj: array) {
            System.out.println("  123: " + obj.toString());
        }


    }
}
