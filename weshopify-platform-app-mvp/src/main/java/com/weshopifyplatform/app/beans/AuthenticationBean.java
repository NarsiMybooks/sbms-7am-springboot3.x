package com.weshopifyplatform.app.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	private boolean isAuthenticated=false;

}
