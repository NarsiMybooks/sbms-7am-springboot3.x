package com.weshopifyplatform.app.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccessTokenReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String client_id;
	private String client_secret;
	private String audience;
	private String grant_type;
	private String username;
	private String password;
	private String scope;

}
