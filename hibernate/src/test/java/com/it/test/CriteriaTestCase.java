package com.it.test;

import com.it.pojo.User;
import com.it.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

/**
 * Created by xieyue on 2016/7/25.
 * CriteriaTestCase
 */


public class CriteriaTestCase {

    @Test
    public void testFindAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        List<User> userList = criteria.list();

        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();

    }

    @Test
    public void testFindByWhere() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        // 等于
        criteria.add(Restrictions.eq("password", "123456"));
        criteria.add(Restrictions.eq("username", "Lisa"));
        // like
        criteria.add(Restrictions.like("username", "l", MatchMode.ANYWHERE));
        // or
        criteria.add(Restrictions.or(
                Restrictions.eq("username", "King"),
                Restrictions.eq("username", "Flash")));

        // or Disjunction
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("username", "King"));
        disjunction.add(Restrictions.eq("username", "Flash"));


        // in
        criteria.add(Restrictions.in("username", new Object[]{"Link", "Tom"}));


        List<User> userList = criteria.list();

        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();


    }

    @Test
    public void testUnique() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        
        criteria.add(Restrictions.eq("username", "Lisa"));
        User user = (User) criteria.uniqueResult();

        session.getTransaction().commit();
        System.out.println("user is " + user);
    }


    @Test
    public void testByPage() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.addOrder(Order.asc("id"));

        criteria.setFirstResult(0);
        criteria.setMaxResults(5);
        List<User> userList = criteria.list();

        for (User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();

    }

    @Test
    public void testCount() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        // rowCount()  是count(*)
        // criteria.setProjection(Projections.rowCount());
        // criteria.setProjection(Projections.count("id"));

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.sum("id"));
        criteria.setProjection(projectionList);


        Object[] objects = (Object[]) criteria.uniqueResult();
        System.out.println("Count: " + objects[0]);
        System.out.println("Sum: " + objects[1]);
        Long total = (Long) objects[1];
        System.out.println("total is " + total);

        session.getTransaction().commit();


    }


}
