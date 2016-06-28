package com.qteng.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by xieyue on 2016/6/27.
 * BatisUtils
 */
public class BatisUtils {

    private static SqlSessionFactory sqlSessionFactory = buildSqlSessionFactory();

    private static SqlSessionFactory buildSqlSessionFactory() {


        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            //  SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("读取文件 mybatis异常", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }


}
