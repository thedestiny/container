package com.it.action;


import com.it.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONAction extends BaseAction {
    Logger logger = LoggerFactory.getLogger(JSONAction.class);


    private Integer id;
    private String name;
    private User user;

    public String execute(){

        id= 101;
        name="queen";
        user = new User();
        user.setUsername("iron");

        return SUCCESS;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
