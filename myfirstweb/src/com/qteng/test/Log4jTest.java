package com.qteng.test;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by xieyue on 2016/6/13.
 */
public class Log4jTest {

    @Test
    public void test(){
        Logger logger = Logger.getLogger(Log4jTest.class);
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
    }

}
