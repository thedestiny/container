package com.it.test;


import com.it.pojo.Department;
import com.it.pojo.Employee;
import com.it.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class OneToManyTestCase {

    @Test
    public void testSave(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Department department = new Department();
        department.setDeptname("Python");

        Employee employee = new Employee();
        employee.setRealname("iron");
        employee.setDepartment(department);

        Employee employee1 = new Employee();
        employee1.setRealname("flash");
        employee1.setDepartment(department);

        session.save(department);
        session.save(employee);
        session.save(employee1);

        session.getTransaction().commit();
    }

    @Test
    public void testFindDepartment(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Department department = (Department) session.get(Department.class,3);
        System.out.println(department.getDeptname());
        Set<Employee> employeeSet = department.getEmployeeSet();
        for(Employee employee :employeeSet){
            System.out.println(employee.getRealname() + " : " + employee.getDepartment().getDeptname());
        }

        session.getTransaction().commit();
    }

    @Test
    public void testFindEmployee(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Employee employee = (Employee) session.get(Employee.class,5);
        System.out.println(employee.getRealname());
        Department department = employee.getDepartment();
        System.out.println(department.getDeptname());

        session.getTransaction().commit();
    }

    @Test
    public void testFindEmployeeAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Employee.class);
        List<Employee> employeeList = criteria.list();

        for(Employee employee : employeeList){
            System.out.println(employee.getId() + " : " + employee.getRealname() + " : " + employee.getDepartment().getDeptname());
        }

        session.getTransaction().commit();

    }

    @Test
    public void testDelete(){

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Department department = (Department) session.get(Department.class,2);
        session.delete(department);

        session.getTransaction().commit();
    }


}
