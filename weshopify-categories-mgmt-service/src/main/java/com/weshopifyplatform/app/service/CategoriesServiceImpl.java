package com.weshopifyplatform.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.weshopifyplatform.app.beans.CategoriesBean;
import com.weshopifyplatform.app.entities.Categories;
import com.weshopifyplatform.app.repo.CategoriesRepository;

@Service
public class CategoriesServiceImpl implements CategoriesService {

	private CategoriesRepository categoriesRepository;

	private ModelMapper modelMapper;

	public CategoriesServiceImpl(CategoriesRepository categoriesRepository, ModelMapper modelMapper) {
		this.categoriesRepository = categoriesRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoriesBean saveCategory(CategoriesBean categoriesBean) {
		Categories categoriesEntity = mapBeanToEntity(categoriesBean);
		categoriesEntity.setEnabled(categoriesBean.isEnabled());
		if(categoriesBean.getParentCatId() != 0) {
			Categories parentCategory = categoriesRepository.findById(categoriesBean.getParentCatId()).get();
			categoriesEntity.setParent(parentCategory);
		}
		categoriesEntity = categoriesRepository.save(categoriesEntity);
		categoriesBean = mapEntityToBean(categoriesEntity);
		return categoriesBean;
	}

	@Override
	public CategoriesBean updateCategory(CategoriesBean categoriesBean) {
		Categories categoriesEntity = mapBeanToEntity(categoriesBean);
		categoriesEntity = categoriesRepository.save(categoriesEntity);
		categoriesBean = mapEntityToBean(categoriesEntity);
		return categoriesBean;
	}

	@Override
	public List<CategoriesBean> deleteCategory(int categoryId) {
		List<CategoriesBean> categoriesBeansList = null;
		categoriesRepository.deleteById(categoryId);
		List<Categories> dbCategories = categoriesRepository.findAll();
		if (!CollectionUtils.isEmpty(dbCategories)) {
			categoriesBeansList = dbCategories.stream().map(category -> mapEntityToBean(category))
					.collect(Collectors.toList());
		}
		return categoriesBeansList;
	}

	@Override
	public CategoriesBean getCategoryById(int categoryId) {
		return mapEntityToBean(categoriesRepository.findById(categoryId).get());
	}

	@Override
	public List<CategoriesBean> getAllCategories() {
		List<CategoriesBean> categoriesBeansList = null;
		List<Categories> dbCategories = categoriesRepository.findAll();
		if (!CollectionUtils.isEmpty(dbCategories)) {
			categoriesBeansList = dbCategories.stream().map(category -> mapEntityToBean(category))
					.collect(Collectors.toList());
		}
		return categoriesBeansList;
	}

	@Override
	public List<CategoriesBean> getAllCategoriesOfAParent(int parentCatId) {
		List<CategoriesBean> categoriesBeansList = null;
		List<Categories> dbCategories = categoriesRepository.findAllChildsOfAParent(parentCatId);
		if (!CollectionUtils.isEmpty(dbCategories)) {
			categoriesBeansList = dbCategories.stream().map(category -> mapEntityToBean(category))
					.collect(Collectors.toList());
		}
		return categoriesBeansList;
	}

	private Categories mapBeanToEntity(CategoriesBean categoriesBean) {
		Categories categoriesEntity = modelMapper.map(categoriesBean, Categories.class);
		return categoriesEntity;
	}

	private CategoriesBean mapEntityToBean(Categories categories) {
		CategoriesBean categoriesBean = modelMapper.map(categories, CategoriesBean.class);
		return categoriesBean;
	}
}
