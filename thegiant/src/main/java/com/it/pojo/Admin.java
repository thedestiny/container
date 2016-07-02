package com.it.pojo;

import java.io.Serializable;

public class Admin implements Serializable{
    private static final long serialVersionUID = 1462149180477536673L;
    private Integer id;
    private String account;
    private String password; // 未加密密码
    private String pwd;  // 加密密码
    private String email; // 邮箱

    public Admin(String account, String password, String pwd, String email) {
        this.account = account;
        this.password = password;
        this.pwd = pwd;
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
