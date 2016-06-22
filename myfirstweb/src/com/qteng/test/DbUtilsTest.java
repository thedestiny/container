package com.qteng.test;

import com.qteng.dao.CountDao;
import com.qteng.entity.Count;
import org.junit.Test;

/**
 * Created by xieyue on 2016/6/8.
 * DbUtilsTest
 */
public class DbUtilsTest {
    CountDao countDao = new CountDao();

    @Test
    public void operation() {

       // CountDao countDao = new CountDao();
        Count count = new Count("queen","123456");
        countDao.adduser(count);
    }


}
