package com.it.pojo;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/7/11.
 * Notice
 */


public class Notice implements Serializable {

    private static final long serialVersionUID = -5989274971626190443L;
    private Integer id;
    private Integer userid;
    private String title;
    private String content;
    private String publisher;
    private String keyword;
    private String level;
    private String publishtime;

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", userid=" + userid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publisher='" + publisher + '\'' +
                ", keyword='" + keyword + '\'' +
                ", level='" + level + '\'' +
                ", publishtime='" + publishtime + '\'' +
                '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }
}
