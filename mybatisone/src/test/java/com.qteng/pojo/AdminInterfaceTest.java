package com.qteng.pojo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qteng.Temple;
import com.qteng.mapper.AdminMapper;
import com.qteng.utils.BatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


/**
 * Created by xieyue on 2016/6/28.
 * AdminInterfaceTest
 */
public class AdminInterfaceTest {

    Logger logger = LoggerFactory.getLogger(AdminInterfaceTest.class);

    @Test
    public void queryByIdTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        Admin admin = adminMapper.queryById(2);
        logger.debug("admin is {}", admin);
        Assert.assertNotNull(admin);
        sqlSession.close();


//    void delById(Integer id);

//    void updateAdmin(Admin admin);
//    Integer insertA(Admin admin);
    }

    @Test
    public void queryAllTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        List<Admin> adminList = adminMapper.queryAll();
        Assert.assertEquals(adminList.size(), 4);
    }

    @Test
    public void insertATest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = new Admin("spark", "save", "sdf", "123456");
        // 默认返回值是影响的行数
        adminMapper.insertA(admin);
        sqlSession.commit();
        sqlSession.close();
        logger.debug("id is {}", admin.getId());
    }

    @Test
    public void delByIdTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        adminMapper.delById(14);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = adminMapper.queryById(14);
        admin.setPwd("asdfsdfsadfsdf");
        adminMapper.updateAdmin(admin);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void queryByMapTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Map<String, Object> map = Maps.newHashMap();
        map.put("account", "flash");
        map.put("email", "123456");

        //  List<Admin> adminList = adminMapper.queryByMap(map);
        // List<Admin> adminList = adminMapper.queryByParams("flash","123456");
        List<Admin> adminList = adminMapper.queryByAno("flash", "123456");
        logger.debug(" admin is {}", adminList.get(0));
        Assert.assertEquals(adminList.size(), 1);
    }

    @Test
    public void insertAdminsTest() {
        // batch 批量
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        List<Admin> adminList = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            Admin admin = new Admin("sparks" + i, "save", "sdf", "123456");
            adminList.add(admin);
        }
        Integer n = adminMapper.insertAdmins(adminList);
        logger.debug("n is {} and ", n);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findAdminByParamsTest() {
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Map<String, Object> map = Maps.newHashMap();
        map.put("account", "s");
        map.put("password", "save");
        List<Admin> adminList = adminMapper.findAdminByParams(map);
        logger.debug(" adminList size is {}",adminList.size());
        for (Admin admin : adminList) {
            System.out.println(admin);
        }
    }

    @Test
    public void findAdminByIdsTest(){
        SqlSession sqlSession = BatisUtils.getSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        List<Integer> id = Lists.newArrayList(2,3,4,5,6,7,8);
        List<Admin> adminList = adminMapper.findAdminByIds(id);
        for (Admin admin : adminList) {
            System.out.println(admin);
        }



    }
}
