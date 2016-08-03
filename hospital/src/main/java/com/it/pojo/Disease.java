package com.it.pojo;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_disease")
public class Disease implements Serializable {

    private static final long serialVersionUID = -4966517135172925796L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sick;
    @ManyToOne
    @JoinColumn(name = "deptid")
    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSick() {
        return sick;
    }

    public void setSick(String sick) {
        this.sick = sick;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
