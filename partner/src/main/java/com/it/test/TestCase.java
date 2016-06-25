package com.it.test;

import org.joda.time.DateTime;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by xieyue on 2016/6/25.
 * TestCase
 */
public class TestCase {

    @Test
    public void temple(){
        File file = new File("G:/issue/aaa");
        try {
            if(file.createNewFile()){
                System.out.println("success !");
            } else {
                System.out.println("failure !");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
