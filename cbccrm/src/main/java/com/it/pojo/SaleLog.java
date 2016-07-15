package com.it.pojo;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/7/15.
 */


public class SaleLog implements Serializable {

    private static final long serialVersionUID = -3066912131647715603L;
    private Integer id;
    private Integer saleid;
    private String context;
    private String createtime;
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSaleid() {
        return saleid;
    }

    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
