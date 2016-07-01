package com.qteng.aop;

/**
 * Created by xieyue on 2016/6/30.
 * Information
 */


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
@Aspect
public class Information {
    Logger logger = LoggerFactory.getLogger(Information.class);

    @Pointcut("execution(* com.qteng.mapper..*.*(..))")
    public void myPointCut(){}

    @Before("myPointCut()")
    public void beforeInf() {
        logger.debug("前置通知++++++++++++");
    }
    @AfterReturning(pointcut ="myPointCut()",returning = "result")
    public void afterReturningInf(Object result) {
        logger.debug("后置通知++++++++++++{}", result);
    }
    @AfterThrowing(pointcut = "myPointCut()",throwing = "e")
    public void exceptionInf(Exception e) {
        logger.debug(" encounter exception and detail is {}", e.getMessage());
    }
    @After("myPointCut()")
    public void finallyInf() {
        logger.debug(" this is the end ===========");
    }
   //  @Around("myPointCut()")
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
