package com.it.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String realname;
    // 延迟加载时 使用
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptid")
    @OrderBy("id desc")
    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
