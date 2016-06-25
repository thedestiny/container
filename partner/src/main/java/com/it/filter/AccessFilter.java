package com.it.filter;


import com.it.utils.Config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xieyue on 2016/6/20.
 * AccessFilter
 */
@WebFilter(urlPatterns = "/*")
public class AccessFilter implements Filter {

    private static final String IGNORE_URI = "filter.ignore";
    private static final String URI_SEPARATOR = "/";
    private Set<String> ignoreUris = new HashSet<>();

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
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        boolean flag = false;
        HttpSession session = request.getSession();
//        if (!ignoreUris.contains(uri)) {
//            flag = true;
//        }
//        if(session.getAttribute("") != null){
//            flag = true;
//        }
//        if(flag){
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            response.sendRedirect("/");
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
