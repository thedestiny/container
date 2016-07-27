package com.it.pojo;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String deptname;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employeeSet;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptname='" + deptname + '\'' +
                ", employeeSet=" + employeeSet +
                '}';
    }
}
