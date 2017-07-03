package com.san.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

public class ExceptionHandlerFilter implements Filter {

	Logger logger = Logger.getLogger(ExceptionHandlerFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		logger.trace("inside doFilter()");
	}

	public void destroy() {
		logger.trace("inside destroy()");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.trace("inside init()");
	}

}
