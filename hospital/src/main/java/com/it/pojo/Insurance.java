package com.it.pojo;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_insurance")
public class Insurance implements Serializable{

    private static final long serialVersionUID = 9222977344644045648L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String card;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
