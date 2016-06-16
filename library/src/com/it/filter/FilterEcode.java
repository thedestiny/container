package com.it.filter;

import com.it.utils.Config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterEcode implements Filter {

	private String code;
    public FilterEcode() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(code);
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		// code = fConfig.getInitParameter("code");
		code = Config.get("filter.code");
	}

}
