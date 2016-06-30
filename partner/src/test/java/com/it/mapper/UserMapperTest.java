package com.it.mapper;

import com.it.pojo.User;
import com.it.utils.BatisUtil;
import com.it.utils.SmallUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/29.
 * UserMapperTest
 */
public class UserMapperTest {

    Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
    @Test
    public void insertUserTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User("iron","xieyue86@163.com","123456","123456", SmallUtils.getTime());
        userMapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateInfoTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // User user = new User("1991-12-25","engineer","USA Star","13849862"," I am Iron","male");
        User user = new User();
        user.setEmail("xieyue86@163.com");
        user.setUsername("iron");
        userMapper.updateInfo(user);
        userMapper.updateAcc("iron");
        userMapper.updateQue("iron");
        userMapper.updateAns("iron");
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void queryUsernameTest(){
        SqlSession sqlSession = BatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("iron");
        user.setMd5pwd("789456");
        user.setAddress("USA");
        userMapper.updatePwd(user);
        userMapper.updateInfo(user);
        User user1 = userMapper.queryUsername("iron");
        logger.debug("user is {}",user1);


    }


}
