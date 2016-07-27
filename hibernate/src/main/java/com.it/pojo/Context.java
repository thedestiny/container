package com.it.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_context")
public class Context {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private Integer titleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTitleid() {
        return titleid;
    }

    public void setTitleid(Integer titleid) {
        this.titleid = titleid;
    }
}
