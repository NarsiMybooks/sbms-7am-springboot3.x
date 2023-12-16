package com.weshopifyplatform.app.beans;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1968199031445617837L;
	
	private String message;
	private int code;
	private Date timeStamp;

}
