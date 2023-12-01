package com.weshopifyplatform.app.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerBean implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -2697815831748607581L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String mobile;
	private String role;
}
