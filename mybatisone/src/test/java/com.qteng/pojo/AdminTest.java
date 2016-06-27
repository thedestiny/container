package com.qteng.pojo;

import com.qteng.utils.BatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by xieyue on 2016/6/27.
 * AdminTest
 */
public class AdminTest {


    Logger logger = LoggerFactory.getLogger(AdminTest.class);

    @Test
    public void testSelect() {

        SqlSessionFactory sqlSessionFactory = BatisUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Admin admin = sqlSession.selectOne("com.qteng.mapper.AdminMapper.queryById", 1);
        // selectOne selectList
        Assert.assertNotNull(admin);
        logger.debug(" admin is {}", admin);

        List<Admin> adminList = sqlSession.selectList("com.qteng.mapper.AdminMapper.queryAll");
        Assert.assertEquals(adminList.size(), 3);
        logger.debug(" admin 3 is {} ",adminList.get(1));
        sqlSession.close();

    }

    @Test
    public void testDel(){
        SqlSession sqlSession = BatisUtils.getSqlSession();
        sqlSession.delete("com.qteng.mapper.AdminMapper.delById",1);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = BatisUtils.getSqlSession();
        Admin admin = new Admin("flash","save","sdf","123456");
        sqlSession.insert("com.qteng.mapper.AdminMapper.insertAdmin",admin);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = BatisUtils.getSqlSession();
        Admin admin = sqlSession.selectOne("com.qteng.mapper.AdminMapper.queryById", 5);
        admin.setPassword("123456");
        admin.setPwd("7894561");
        sqlSession.update("com.qteng.mapper.AdminMapper.updateAdmin",admin);
        sqlSession.commit();
        sqlSession.close();


    }


}
