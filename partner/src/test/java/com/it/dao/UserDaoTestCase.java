package com.it.dao;

import com.it.entity.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;

/**
 * Created by xieyue on 2016/6/27.
 * UserDaoTestCase
 */
public class UserDaoTestCase {

    @Test
    public void writeUserTestCase() {
        User user = new User("jim", "xieyue86@163.com", "123456", "123456", "2016-06-27");
        File file = new File("G:/issue/", "user");

    }

}
