package com.it.pojo;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/7/13.
 * Custom
 */


public class Custom implements Serializable{


    public static final String CUSTOM_TYPE_PERSON = "person";
    public static final String CUSTOM_TYPE_COMPANY = "company";
    private static final long serialVersionUID = 2518026674894876101L;

    private Integer id;
    private Integer userid;
    private String customer;
    private String tel;
    private String weixin;
    private String address;
    private Integer dependid;
    private String level;
    private String company;
    private String realname;
    private String createtime;
    private String pinyin;
    private String type;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Custom() {
    }

    public Custom(Integer userid) {
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDependid() {
        return dependid;
    }

    public void setDependid(Integer dependid) {
        this.dependid = dependid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
