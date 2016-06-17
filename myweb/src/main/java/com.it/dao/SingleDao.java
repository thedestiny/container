package com.it.dao;

/**
 * Created by xieyue on 2016/6/17.
 *
 */
public class SingleDao {
    private static SingleDao ourInstance = new SingleDao();

    public static SingleDao getInstance() {
        return ourInstance;
    }

    private SingleDao() {

    }
}
