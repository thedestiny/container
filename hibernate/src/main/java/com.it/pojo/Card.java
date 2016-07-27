package com.it.pojo;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "t_card")
public class Card {

    @Id
    @GenericGenerator(name="fkey",strategy = "foreign",
            parameters = @Parameter(name="property",value = "person"))
    @GeneratedValue(generator = "fkey")
    private Integer id;
    private Integer cardnum;
    @OneToOne(mappedBy = "card")
    @PrimaryKeyJoinColumn
    private Person person;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardnum() {
        return cardnum;
    }

    public void setCardnum(Integer cardnum) {
        this.cardnum = cardnum;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
