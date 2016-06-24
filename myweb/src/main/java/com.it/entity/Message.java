package com.it.entity;

/**
 * Created by xieyue on 2016/6/24.
 * Message
 */
public class Message {
    private Integer id;
    private String msg;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Message(String msg, String author) {
        this.msg = msg;
        this.author = author;
    }

    public Message() {
    }
}
