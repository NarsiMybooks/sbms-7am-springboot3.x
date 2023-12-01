package com.weshopifyplatform.app.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
