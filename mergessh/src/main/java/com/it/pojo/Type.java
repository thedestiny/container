package com.it.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_type")
public class Type implements Serializable {

    private static final long serialVersionUID = -5257728077499481700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
