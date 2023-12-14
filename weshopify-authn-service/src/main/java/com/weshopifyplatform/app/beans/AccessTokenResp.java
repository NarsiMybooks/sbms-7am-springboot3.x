package com.weshopifyplatform.app.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccessTokenResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String access_token;
	private String refresh_token;
	private String id_token;
	private String scope;
	private long expires_in;
	private String token_type;

}
