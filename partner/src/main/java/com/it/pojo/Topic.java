package com.it.pojo;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/6/29.
 * Topic
 */


public class Topic implements Serializable {

    private static final long serialVersionUID = -2038053414259190431L;
    private Integer id;
    private Integer issueid;
    private String theme;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIssueid() {
        return issueid;
    }

    public void setIssueid(Integer issueid) {
        this.issueid = issueid;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
