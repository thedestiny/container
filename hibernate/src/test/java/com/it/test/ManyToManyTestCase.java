package com.it.test;


import com.it.pojo.Student;
import com.it.pojo.Teacher;
import com.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ManyToManyTestCase {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Teacher teacher1 = new Teacher();
        teacher1.setTeaname("jack");
        Teacher teacher2 = new Teacher();
        teacher2.setTeaname("lily");
        Set<Teacher> teacherSet = new HashSet<Teacher>();
        teacherSet.add(teacher1);
        teacherSet.add(teacher2);

        Student student1 = new Student();
        student1.setStuname("queen");

        Student student2 = new Student();
        student2.setStuname("ostan");

        student1.setTeacherSet(teacherSet);
        student2.setTeacherSet(teacherSet);

        session.save(teacher1);
        session.save(teacher2);
        session.save(student1);
        session.save(student2);

        session.getTransaction().commit();
    }

    @Test
    public void testFindTeacher(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Teacher teacher = (Teacher) session.get(Teacher.class,7);
        System.out.println(teacher.getTeaname());
        Set<Student> studentSet = teacher.getStudentSet();
        for (Student student : studentSet) {
            System.out.println(student.getId() + " : " + student.getStuname());
        }

        session.getTransaction().commit();
    }
    @Test
    public void testFindStudent(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Student student = (Student) session.get(Student.class,6);
        System.out.println(student.getStuname());
        Set<Teacher> teacherSet = student.getTeacherSet();
        for (Teacher teacher : teacherSet) {
            System.out.println(teacher.getId() + " : " + teacher.getTeaname());
        }

        session.getTransaction().commit();
    }


}
