package com.weshopifyplatform.app.beans;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class BrandsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3967216733163141688L;

	private String id;
	
	private String name;
	private String logo;
	private List<CategoriesBean> categories;
	

	
}
