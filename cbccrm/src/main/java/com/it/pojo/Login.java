package com.it.pojo;

/**
 * Created by xieyue on 2016/7/8.
 */


public class Login {

    private Integer id;
    private String logintime;
    private String loginip;
    private Integer userid;


    public Login(String logintime, String loginip, Integer userid) {
        this.logintime = logintime;
        this.loginip = loginip;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", logintime='" + logintime + '\'' +
                ", loginip='" + loginip + '\'' +
                ", userid=" + userid +
                '}';
    }
}
