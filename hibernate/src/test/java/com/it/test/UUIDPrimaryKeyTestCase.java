package com.it.test;


import com.it.pojo.Task;
import com.it.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class UUIDPrimaryKeyTestCase {

    @Test
    public void testSave(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = new Task();
        task.setContent("iron");
        session.save(task);

        session.getTransaction().commit();

    }

    @Test
    public void testLock1(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"4028b881562bd56101562bd563f30000");
        System.out.println(task.getId() +"->" + task.getContent());
        task.setContent("tom is jim");

        session.getTransaction().commit();


    }




    @Test
    public void testLock() throws InterruptedException {


        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"4028b881562badcf01562badd1c20000", LockOptions.UPGRADE);
        System.out.println(task.getContent());
        task.setContent("tom is tom");

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Session session1 = HibernateUtil.getSession();
                session1.beginTransaction();

                Task task1 = (Task) session1.get(Task.class,"4028b881562badcf01562badd1c20000");
                task1.setContent("jim is bigjim");

                session1.getTransaction().commit();
            }
        });
        thread.start();

        Thread.sleep(5000);
        System.out.println(session.contains(task));
        session.getTransaction().commit();
    }

    @Test
    public void testFindById(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"4028b881562badcf01562badd1c20000");
        System.out.println(task.getContent());
        // 清除指定缓存
        // session.evict(task);
        System.out.println(session.contains(task));
        session.getTransaction().commit();

        // close() clear() 能清除一级缓存

        //将对象从二级缓存中清除
        Cache cache = HibernateUtil.getSessionFactory().getCache();
        cache.evictEntityRegion(Task.class);
        cache.evictAllRegions();

        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();

        Task task1 = (Task) session1.get(Task.class,"4028b881562badcf01562badd1c20000");
        System.out.println(task1.getContent());
        session1.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        String hql = "from Task";
        Query query = session2.createQuery(hql);
        List<Task> taskList = query.list();
        for (Task task2 : taskList){
            System.out.println(task2.getId() + "->" + task2.getContent());
        }

        session2.getTransaction().commit();

    }





}
