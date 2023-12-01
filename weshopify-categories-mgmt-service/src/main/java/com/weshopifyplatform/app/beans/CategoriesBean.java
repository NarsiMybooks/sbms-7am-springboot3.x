package com.weshopifyplatform.app.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoriesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2857332108550225557L;

	private int id;

	private String name;
	private String aliasName;
	private String imagePath;
	private boolean isEnabled;
	private int parentCatId;

}
