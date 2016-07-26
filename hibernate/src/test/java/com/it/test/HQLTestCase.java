package com.it.test;

import com.it.pojo.User;
import com.it.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

/**
 * Created by xieyue on 2016/7/25.
 * HQLTestCase
 */


public class HQLTestCase {

    @Test
    public void testFindAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql ="from User";

        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        session.getTransaction().commit();

        for (User user : userList) {
            System.out.println(user);
        }

    }


    @Test
    public void testFindByWhere(){
        // HQL 是java对象，与数据库无关
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        // 引用占位符
       // String hql = "from User as u where u.username = :username and u.password = :password";
        String hql = "from User as u where u.username = ? and u.password = ? ";
        Query query = session.createQuery(hql);

        // query.setParameter("username","King");
        // query.setParameter("password","123456");

        query.setParameter(0,"King");
        query.setParameter(1,"123456");
        List<User> userList = query.list();

        session.getTransaction().commit();

        for (User user : userList) {
            System.out.println(user);
        }

    }

    @Test
    public void testFindUnique(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User where password = :pwd";

        Query query = session.createQuery(hql);

        query.setParameter("pwd","78945613");

        User user = (User) query.uniqueResult(); //确保结果集只有一条记录
        System.out.println(user);

        session.getTransaction().commit();

    }

    @Test
    public void testFindByCloumn(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        // 单列是 List 集合 多列是数组集合
        String hql = "select id,username,password from User";
        Query query = session.createQuery(hql);

        List<Object[]> result = query.list();

        session.getTransaction().commit();

        for (Object[] objects : result) {
            System.out.println(objects[0] + " -> " + objects[1]);
        }
    }

    @Test
    public void testCount(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();


        String hql = "select count(*) , min(id) from User";

        Query query = session.createQuery(hql);

        Object[] objects = (Object[]) query.uniqueResult();

        session.getTransaction().commit();

        System.out.println("Count:" + objects[0]);
        System.out.println("Max:" + objects[1]);

    }

    @Test
    public void testPage(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User order by id desc";

        Query query = session.createQuery(hql);
        query.setFirstResult(3);
        query.setMaxResults(3);

        List<User> userList = query.list();
        for(User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();




    }





}
