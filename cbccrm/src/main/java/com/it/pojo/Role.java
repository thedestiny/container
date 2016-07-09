package com.it.pojo;

import java.io.Serializable;

/**
 * Created by xieyue on 2016/7/8.
 * Role
 */


public class Role implements Serializable {

    private static final long serialVersionUID = 1788746974888725778L;
    private Integer id;
    private String rolename;

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
