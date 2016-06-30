package com.it.pojo;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/6/29.
 * Tag
 */


public class Tag implements Serializable {

    private static final long serialVersionUID = -8529072731477700688L;
    private Integer id;
    private Integer userid;
    private String tag;

    public Tag() {
    }

    public Tag(Integer userid, String tag) {
        this.userid = userid;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
