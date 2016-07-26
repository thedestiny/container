package com.it.test;

/**
 * Created by xieyue on 2016/7/25.
 * HibernateLifeTestCase
 */


import com.it.pojo.User;
import com.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class HibernateLifeTestCase {

    @Test
    public void testSave() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("King");
        user.setRealname("king");
        user.setPassword("123456");

        session.persist(user);
        System.out.println("user's id is " + user.getId());

        session.getTransaction().commit();
    }

    @Test
    public void testFindByGet(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,6);

        session.getTransaction().commit();

        Assert.assertNotNull(user);
    }

    @Test
    public void testFindByLoad(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.load(User.class,17);
        System.out.println("user's username is " + user.getRealname());

        session.getTransaction().commit();
    }

    @Test
    public void testUpdate(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.load(User.class,7);
        session.getTransaction().commit();

        user.setPassword("456123");

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();
        session1.update(user);
        session1.saveOrUpdate(user);

        session1.getTransaction().commit();

    }

    @Test
    public void testSaveOrUpdate(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
//
        User user = new User();
        user.setId(15);
        user.setUsername("Link");
        user.setPassword("456789");
        user.setRealname("trim");

        User user1 = (User) session.get(User.class,15);

        user.setPassword("789456");
        // session.saveOrUpdate(user);
        session.merge(user);

        session.getTransaction().commit();

        user1.setPassword("78945613");

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        session1.saveOrUpdate(user1);

        session1.getTransaction().commit();

    }

    @Test
    public void testMerge(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
//        如果对象有主键 更新，没有主键 插入
//        User user = new User();
//        user.setUsername("Link");
//        user.setPassword("456789");
//        user.setRealname("link");
//        session.merge(user);

         User user = (User) session.get(User.class,6);

        session.getTransaction().commit();

        user.setPassword("789456132");

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        session2.merge(user);

        session2.getTransaction().commit();

    }

    @Test
    public void testDelete(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.get(User.class,6);
        // 持久态进入自由态
        session.delete(user);
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        // 进入游离态
        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();
        // 游离态 变为 自由态
        session2.delete(user);
        session2.getTransaction().commit();

    }

    @Test
    public void testClear(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.get(User.class,7);
       //  清除session 中全部对象
        //session.clear();

        user.setPassword("65329745");
        // 立即同步到数据库
        session.flush();
        session.getTransaction().commit();
    }




}
