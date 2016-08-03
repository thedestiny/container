package com.it.action;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class LoginInterceptor extends AbstractInterceptor {


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {


        ActionProxy actionProxy = invocation.getProxy();
        String namespace = actionProxy.getNamespace();
        String actionname = actionProxy.getActionName();

        if (("/".equals(namespace) && (actionname.startsWith("index") || actionname.startsWith("login")))
                || namespace.startsWith("/index")) {
            return actionProxy.execute();
        } else {
            Map<String, Object> session = ActionContext.getContext().getSession();
            String sessionValue = (String) session.get("curr_user");
            return sessionValue != null ? actionProxy.execute() : Action.LOGIN;

        }

    }
}
