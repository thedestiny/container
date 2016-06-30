package com.qteng.aop;

/**
 * Created by xieyue on 2016/6/30.
 * Information
 */


import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Information {
    Logger logger = LoggerFactory.getLogger(Information.class);

    public void beforeInf() {
        logger.debug("前置通知++++++++++++");
    }

    public void afterReturningInf(Object result) {
        logger.debug("后置通知++++++++++++{}", result);
    }

    public void exceptionInf(Exception e) {
        logger.debug(" encounter exception and detail is {}", e.getMessage());
    }

    public void finallyInf() {
        logger.debug(" this is the end ===========");
    }

    public Object aroundInf(ProceedingJoinPoint joinPoint) {
        Object object = null;
        try {
            logger.debug(" this is aroundInf start");
            object = joinPoint.proceed();
            logger.debug("this is aroundInf normal over");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.debug(" this is aroundInf exception  ");
        } finally {
            logger.debug("this is aroundInf end ");
        }
        return object;
    }


}
