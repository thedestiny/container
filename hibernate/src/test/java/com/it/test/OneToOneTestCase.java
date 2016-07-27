package com.it.test;


import com.it.pojo.Card;
import com.it.pojo.Person;
import com.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOneTestCase {


    @Test
    public void testSave(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = new Person();
        person.setUsername("jly");

        Card card = new Card();
        card.setCardnum(139965395);
        card.setPerson(person);

        session.save(person);
        session.save(card);

        session.getTransaction().commit();
    }

    @Test
    public void testFindPerson(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = (Person) session.get(Person.class,1);

        System.out.println(person.getUsername());
        System.out.println(person.getCard().getCardnum());

        session.getTransaction().commit();
    }

    @Test
    public void testFindCard(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Card card = (Card) session.get(Card.class,2);
        System.out.println(card.getCardnum());
        System.out.println(card.getPerson().getUsername());
        session.getTransaction().commit();
    }

    @Test
    public void testDeleteCard(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        // 删除卡
//        Card card = (Card) session.get(Card.class,4);
//        session.delete(card);

        // 删除人
        Person person = (Person) session.get(Person.class,6);
        session.delete(person);

        session.getTransaction().commit();
    }


}
