package com.it.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by xieyue on 2016/6/29.
 * BatisUtil
 */
public class BatisUtil {
    private static SqlSessionFactory sqlSessionFactory = buileSqlSessionFactory();

    private static SqlSessionFactory buileSqlSessionFactory() {

        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("读取mybatis.xml文档失败", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}