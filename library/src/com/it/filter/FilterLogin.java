package com.it.filter;

import com.it.utils.Config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FilterLogin implements Filter {

	private String uris;
    public FilterLogin() {

    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		String[] strs = uris.split(",");
		boolean  flag = false;
		for(String str : strs){
			if(uri.equals(str)){
				flag = true;
				break;
			}
		}
		if(flag){
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession();
			// Object name = session.getAttribute("name");
			if(session.getAttribute("name") == null){
				res.sendRedirect("/login");
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// uris = fConfig.getInitParameter("uris");
		uris= Config.get("filter.uris");
	}

}
