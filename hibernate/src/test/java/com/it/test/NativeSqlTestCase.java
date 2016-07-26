package com.it.test;


import com.it.pojo.User;
import com.it.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class NativeSqlTestCase {

    @Test
    public void testFindAll(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user";
        SQLQuery  sqlQuery = session.createSQLQuery(sql);

        List<Object[]> result = sqlQuery.list();

        session.getTransaction().commit();

        for(Object[] objects : result) {
            System.out.println(objects[0] + " : " + objects[1] + " : " + objects[2]);

        }

    }

    @Test
    public void testFindById(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String sql = "select * from t_user WHERE id = :id";
        // SQLQuery  sqlQuery = session.createSQLQuery(sql);
        SQLQuery  sqlQuery = session.createSQLQuery(sql).addEntity(User.class);
        sqlQuery.setInteger("id",15);
        // Object[] objects = (Object[]) sqlQuery.uniqueResult();
        User user = (User) sqlQuery.uniqueResult();
        System.out.println(user);
        session.getTransaction().commit();
       // System.out.println(objects[0] + "->"+objects[1] + "->"+objects[2]);




    }







}
