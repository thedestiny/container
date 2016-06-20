package com.it.filter;


import com.it.utils.Config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xieyue on 2016/6/19.
 * CharsetEncodeFilter
 * 属性filterName 是过滤器名称，可选
 * 属性urlPatterns 指定过滤的URL模式，也可以使用value来声明，URL模式必选
 */
@WebFilter(filterName="CharsetEncodeFilter",urlPatterns="/*")
public class CharsetEncodeFilter implements Filter {

    private static final String IGNORE_URI = "filter.ignore";
    private static final String URI_SEPARATOR = "/";
    private  Set<String> ignoreUris = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String originalUris = Config.get(IGNORE_URI);
        if (originalUris == null) {
           return;
        }
        String[] uris = originalUris.split(URI_SEPARATOR);
        for (String uri : uris) {
            this.ignoreUris.add(uri);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        System.out.println("uri is : " + uri);
        if (!ignoreUris.contains(uri)) {
            // System.out.println("调用方法 : " + request.getMethod());
            if (request.getMethod().equals("GET")) {
                request = new EncodingRequest(request);
            } else {
                System.out.println("post 设置");
                request.setCharacterEncoding("UTF-8");
            }
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
class EncodingRequest extends HttpServletRequestWrapper {

    public EncodingRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (value != null) {
            try {
                value = new String(value.getBytes("ISO8859-1"), "UTF-8");
                // System.out.println("value is : " + value);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("ISO8859-1 transform UTF-8 encounter exception", e);
            }
        }
        return value;
    }
}
