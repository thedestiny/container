package com.it.action;


import com.google.common.collect.Lists;
import com.it.pojo.User;

import java.util.List;

public class UserAction {

    private User user;
    private List<String> names;


    public String toPage(){
        System.out.println("UserAction save!");
        return "success";
    }

    public String save(){
        System.out.println("username is " + user.getUsername() + "and address :" + user.getAddress());
        return "success";
    }


    public String list(){

        names = Lists.newArrayList();
        names.add("李云龙");
        names.add("赵刚");
        names.add("刘伯承");
        names.add("丁伟");

        return "success";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
