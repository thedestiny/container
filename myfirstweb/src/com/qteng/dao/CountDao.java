package com.qteng.dao;

import com.qteng.entity.Count;
import com.qteng.utils.Dbhelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.junit.Test;

import java.util.List;

/**
 * Created by xieyue on 2016/6/8.
 */
public class CountDao {


    public void adduser(Count count) {
        String sql = "insert into count (user,password) value(?,?)";
        Dbhelper.update(sql, count.getUser(), count.getPassword());
    }

    public void updateuser(Count count) {
        String sql = "update count set pwd = ? where id = ? ";
        Dbhelper.update(sql, count.getPwd(), count.getId());
    }

    public List<Count> showusers() {
        String sql = "select * from count";
        return Dbhelper.query(sql, new BeanListHandler<>(Count.class));
    }

    public Count findUserbyId(Integer id) {
        String sql = "select * from count where id = ?";
        return Dbhelper.query(sql, new BeanHandler<>(Count.class), id);
    }

    public List<Integer> queryUserNames() {
        String sql = "select id from count";
        return Dbhelper.query(sql, new ColumnListHandler<Integer>());
    }

    public boolean login(String user, String pwd) {
        String salt = "asdfa23nadsvdafdfg";
        pwd = DigestUtils.md5Hex(pwd + salt);
        String sql = "select * from count where user=? and pwd =?";
        return Dbhelper.query(sql, new BeanHandler<>(Count.class), user, pwd) != null;
    }

    public Count login(String user) {
        String sql = "select * from count where user=?";
        return Dbhelper.query(sql, new BeanHandler<>(Count.class), user);
    }

    @Test
    public void updatepwd() {
        String salt = "asdfa23nadsvdafdfg";
        for (int i = 1; i < 10; i++) {
            Count count = findUserbyId(i);
            if (count != null) {
                String code = count.getPassword();
                code = code + salt;
                count.setPwd(DigestUtils.md5Hex(code));
                updateuser(count);
            } else {
                continue;
            }
        }
        System.out.println("success!");

    }


//    public Long usersCount() {
//        String sql = "select count(*) from count";
//
//        try {
//            return queryRunner.query(connection, sql, new ScalarHandler<Long>());
//        } catch (SQLException e) {
//            throw new DataAccessException("查询总数出现异常", e);
//        } finally {
//            ConnectionManager.closeConnection(connection);
//        }
//    }


//    public void queryTom() {
//        // String sql = "select * from count where user=?";
//        String sql2 = "select * from count ";
//
//        try {
////            Map<String, Object> map = queryRunner.query(connection, sql, new MapHandler(), "tom");
////            for (Map.Entry<String, Object> entry : map.entrySet()) {
////                System.out.println(entry.getKey() + "     " + entry.getValue());
////            }
//
//
//            List<Map<String, Object>> list = queryRunner.query(connection, sql2, new MapListHandler());
//            for (Map<String, Object> map : list) {
//                for (Map.Entry<String, Object> entry : map.entrySet()) {
//                    System.out.println(entry.getKey() + "     " + entry.getValue());
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new DataAccessException("查询tom出现异常", e);
//        } finally {
//            ConnectionManager.closeConnection(connection);
//        }


}



