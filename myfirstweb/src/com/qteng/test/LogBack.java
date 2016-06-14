package com.qteng.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xieyue on 2016/6/13.
 */
public class LogBack {

    @Test
    public void  logback(){

            Logger logger = LoggerFactory.getLogger(LogBack.class);
            logger.trace("trace log");
            logger.debug("debug log");
            logger.info("{}info log{}",123,"456");
            logger.warn("warn log");
            logger.error("error log");

    }
}
