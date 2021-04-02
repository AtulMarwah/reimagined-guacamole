/*package com.spring.boot.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class CachingRequestBodyFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
	        MultipleReadHttpRequest wrappedRequest = new MultipleReadHttpRequest(currentRequest);
	        chain.doFilter(wrappedRequest, servletResponse);
		
	}
	
	
}*/