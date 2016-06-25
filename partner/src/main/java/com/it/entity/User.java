package com.it.entity;

/**
 * Created by xieyue on 2016/6/25.
 * User 注册完成时将Register数据复制过来，并删除Register中的数据
 * 完善个人资料时填写剩余内容
 */
public class User {

    private String id;
    private String username;
    private String email;
    private String password;
    private String md5pwd;
    private String time;
    private String brith;
    private String job;
    private String address;
    private String tel;
    private String resume;
    private String sex;
    private Integer quenum;
    private Integer ansnum;
    private Integer accept;

    public User(String username, String email, String password, String md5pwd, String time) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.md5pwd = md5pwd;
        this.time = time;
    }

    public User(String brith, String job, String address, String tel, String resume, String sex) {
        this.brith = brith;
        this.job = job;
        this.address = address;
        this.tel = tel;
        this.resume = resume;
        this.sex = sex;
    }

    public Integer getQuenum() {
        return quenum;
    }

    public void setQuenum(Integer quenum) {
        this.quenum = quenum;
    }

    public Integer getAnsnum() {
        return ansnum;
    }

    public void setAnsnum(Integer ansnum) {
        this.ansnum = ansnum;
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public User() {
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMd5pwd() {
        return md5pwd;
    }

    public void setMd5pwd(String md5pwd) {
        this.md5pwd = md5pwd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBrith() {
        return brith;
    }

    public void setBrith(String brith) {
        this.brith = brith;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
