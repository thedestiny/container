package com.it.service;

/**
 * Created by xieyue on 2016/7/17.
 * SmallUtilsTestCase
 */


import com.it.utils.SmallUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmallUtilsTestCase {
    Logger logger = LoggerFactory.getLogger(SmallUtilsTestCase.class);

    @Test
    public void testRealtiveTime(){
        DateTime obj = new DateTime(2016, 7, 16, 23, 38, 38, 49);
        if(obj.isBeforeNow()){
            logger.debug("obj 在now 之前");
        }
    }


}
