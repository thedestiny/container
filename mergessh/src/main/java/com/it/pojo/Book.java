package com.it.pojo;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_book")
public class Book implements Serializable {

    private static final long serialVersionUID = -1678014977431377589L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private Float price;
    private Integer total;
    private String code;

    @ManyToOne
    @JoinColumn(name = "pressid")
    private Publisher publisher;
    @ManyToOne
    @JoinColumn(name = "typeid")
    private Type type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book getInstance(){
        return new Book();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }




}
