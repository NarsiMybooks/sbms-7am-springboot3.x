package com.weshopifyplatform.app.entities;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.weshopifyplatform.app.beans.CategoriesBean;

import lombok.Data;

@Document(collection = "weshopify-brands")
@Data
public class Brands implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3205039228002477060L;
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String name;
	private String logo;
	private List<CategoriesBean> categories;
	

	
}
