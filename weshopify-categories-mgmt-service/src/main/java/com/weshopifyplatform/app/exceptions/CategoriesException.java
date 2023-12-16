/**
 * 
 */
package com.weshopifyplatform.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 */

public class CategoriesException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;

	public CategoriesException() {
		super();
	}
	public CategoriesException(String errorMessage) {
		super(errorMessage);
		System.out.println("errorMessage is:\t"+errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	
	

}
