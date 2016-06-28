package com.it.entity;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/6/26.
 * Answer
 */
public class Answer implements Serializable {

    private String user;
    private String time;
    private String content;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer() {
    }

    public Answer(String user, String time, String content) {
        this.user = user;
        this.time = time;
        this.content = content;
    }
}
