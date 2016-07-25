package com.it.test;

import com.it.pojo.User;
import com.it.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

public class HibernateTestCase {


    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setUsername("Jim");
        user.setPassword("123456");
        user.setRealname("jim");
        session.save(user);
        transaction.commit();

    }

    @Test
    public void testUpdate(){

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        User user = (User) session.get(User.class,2);
        user.setUsername("Iron");
        transaction.commit();
    }

    @Test
    public void testDel(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,3);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Test
    public void testFindAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();
        session.getTransaction().commit();
        for(User user : userList){
            System.out.println(user.getId() + "->"+user.getUsername()+ "->"+user.getPassword()+ "->"+user.getRealname());
        }
    }









}
