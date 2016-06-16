package com.qteng.entity;

/**
 * Created by xieyue on 2016/6/8.
 * Count
 */
public class Count {
    private int id;
    private String user;
    private String password;
    private String pwd;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Count(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Count() {
    }

    @Override
    public String toString() {
        return "Count{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
