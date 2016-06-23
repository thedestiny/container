package com.it.entity;

/**
 * Created by xieyue on 2016/6/23.
 * for temple test
 */
public class User {
    private Integer id;
    private String name;
    private String hobby;
    private String major;

    public User() {
    }


    public User(Integer id, String name, String hobby, String major) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
        this.major = major;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
