package com.it.test;


import com.it.pojo.Context;
import com.it.pojo.Title;
import com.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOneTestCase2 {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Context context = new Context();
        context.setContent("hello,word,we are coming!");

        Title title = new Title();
        title.setCaption("标题2");
        title.setContext(context);

//        注意顺序
        session.save(context);
        session.save(title);


        session.getTransaction().commit();
    }

    @Test
    public void testFind(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Title title = (Title) session.get(Title.class,1);
        System.out.println(title.getCaption());
        System.out.println(title.getContext().getContent());
        session.getTransaction().commit();
    }




}
