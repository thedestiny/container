package com.it.pojo;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "t_publisher")
public class Publisher implements Serializable{

    private static final long serialVersionUID = -2465956308604653418L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String press;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
