package com.weshopifyplatform.app.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class TokenValidationFilter extends GenericFilterBean {

	private IdpAuthzService idpAuthzService;
	
	public TokenValidationFilter(IdpAuthzService idpAuthzService) {
		this.idpAuthzService = idpAuthzService;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String tokenWithBearer = httpRequest.getHeader("Authorization");
		if(tokenWithBearer != null) {
			String accessToken = tokenWithBearer.replace("Bearer ", "");
			Authentication authn = idpAuthzService.authzUser(accessToken);
			if(authn != null && authn.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authn);
			}
		}
		chain.doFilter(httpRequest, response);
	}

}
