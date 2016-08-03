package com.it.action;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTimeInterceptor extends AbstractInterceptor {
    Logger logger = LoggerFactory.getLogger(MyTimeInterceptor.class);

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        ActionProxy actionProxy = invocation.getProxy();
        String namespace = actionProxy.getNamespace();
        String methodname = actionProxy.getMethod();
        String name = actionProxy.getActionName();

        long start = System.currentTimeMillis();
        String result = actionProxy.execute();
        long end = System.currentTimeMillis();
        logger.debug("namespace is {}, method is {}, name is {} ,the time is {} ms ",namespace,methodname,name,end -start);

        return result;
    }
}
