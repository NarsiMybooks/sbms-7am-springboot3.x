package com.weshopifyplatform.app.service;

import java.util.List;

import com.weshopifyplatform.app.beans.CategoriesBean;

public interface CategoriesService {

	CategoriesBean saveCategory(CategoriesBean categoriesBean);
	CategoriesBean updateCategory(CategoriesBean categoriesBean);
	List<CategoriesBean> deleteCategory(int categoryId);
	CategoriesBean getCategoryById(int categoryId);
	List<CategoriesBean> getAllCategories();
	List<CategoriesBean> getAllCategoriesOfAParent(int parentCatId);
	
}
