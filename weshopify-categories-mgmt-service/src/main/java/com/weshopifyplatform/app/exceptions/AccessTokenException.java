/**
 * 
 */
package com.weshopifyplatform.app.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 */
public class AccessTokenException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String errorMessage;

	
	public AccessTokenException(String errorMessage) {
		super(errorMessage);
		System.out.println("errorMessage is:\t"+errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	

}
