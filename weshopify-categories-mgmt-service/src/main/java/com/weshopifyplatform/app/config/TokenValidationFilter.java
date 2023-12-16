package com.weshopifyplatform.app.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import com.weshopifyplatform.app.exceptions.AccessTokenException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenValidationFilter extends GenericFilterBean {

	private IdpAuthzService idpAuthzService;
	
	AuthenticationEntryPoint authenticationEntryPoint;
	
	public TokenValidationFilter(IdpAuthzService idpAuthzService,AuthenticationEntryPoint authenticationEntryPoint) {
		this.idpAuthzService = idpAuthzService;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpresp = (HttpServletResponse)response;
		try {
			String tokenWithBearer = httpRequest.getHeader("Authorization");
			if(tokenWithBearer != null) {
				String accessToken = tokenWithBearer.replace("Bearer ", "");
				Authentication authn = idpAuthzService.authzUser(accessToken);
				if(authn != null && authn.isAuthenticated()) {
					SecurityContextHolder.getContext().setAuthentication(authn);
				}
			}
			chain.doFilter(httpRequest, response);
		}catch (AuthenticationException e) {
			if(e instanceof AccessTokenException) {
				authenticationEntryPoint.commence(httpRequest, httpresp, e);
			}else {
				authenticationEntryPoint.commence(httpRequest, httpresp, e);
			}
			

		}
		
	}

}
