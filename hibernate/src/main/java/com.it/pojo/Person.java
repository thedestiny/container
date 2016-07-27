package com.it.pojo;


import javax.persistence.*;

@Entity
@Table(name = "t_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    // ALL 或者 REMOVE 级联删除
    @OneToOne(cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private Card card;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
