package com.it.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public abstract class BaseAction extends ActionSupport{
    Logger logger = LoggerFactory.getLogger(BaseAction.class);

    /**
     * 获取Request对象
     * @return HttpServletRequest
     */
    public HttpServletRequest getHttpServletRequest(){
        return ServletActionContext.getRequest();
    }

    /**
     * 获取Response对象
     * @return HttpServletResponse
     */
    public HttpServletResponse getHttpServletResponse(){
        return ServletActionContext.getResponse();
    }

    /**
     * 获取HttpSession对象
     * @return HttpSession
     */
    public HttpSession getHttpSession(){
        return getHttpServletRequest().getSession();
    }

    /**
     * 获取Session对象
     * @return Session Map集合
     */
    public Map<String,Object> getSession(){
        return ActionContext.getContext().getSession();
    }

    /**
     * 获取Application对象
     * @return Application Map集合
     */
    public Map<String,Object> getApplication(){
        return ActionContext.getContext().getApplication();
    }






}
