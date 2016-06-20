package com.it.test;

import org.junit.Test;

/**
 * Created by xieyue on 2016/6/20.
 * TempleTest
 */
public class TempleTest {

    @Test
    public void temple(){

        String sql = "insert into table (id,name,value) value(?,?,?) where name = ?";
        String regex = "[(, ]\\s*?\\w+\\s*?[,)=]";

    }

}
