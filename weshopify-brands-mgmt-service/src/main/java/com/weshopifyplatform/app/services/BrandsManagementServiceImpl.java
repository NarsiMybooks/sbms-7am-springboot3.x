package com.weshopifyplatform.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weshopifyplatform.app.beans.BrandsBean;
import com.weshopifyplatform.app.beans.CategoriesBean;
import com.weshopifyplatform.app.entities.Brands;
import com.weshopifyplatform.app.outbound.CategoriesOutboundCommunicator;
import com.weshopifyplatform.app.outbound.CategoriesOutboundFeignClient;
import com.weshopifyplatform.app.repos.BrandsDocumentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandsManagementServiceImpl implements BrandsManagementService {

	@Autowired
	private BrandsDocumentRepository brandsRepo;
	
	@Autowired
	private CategoriesOutboundCommunicator categoriesClient;
	
	@Autowired
	private CategoriesOutboundFeignClient catgoriesFeignClient;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public BrandsBean createBrand(BrandsBean brandsBean) {
		Brands brands = mapper.map(brandsBean, Brands.class);
		List<CategoriesBean> categoriesList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(brandsBean.getCategories())) {
			brandsBean.getCategories().stream().forEach(catBean->{
				log.info("category id in create brands:\t"+catBean.getId());
			catBean =	catgoriesFeignClient.getCategoryById(catBean.getId());
			  System.out.println("catgories object:\t"+catBean.toString());
			  categoriesList.add(catBean);
			});
		}
		brands.setCategories(categoriesList);
		brandsRepo.save(brands);
		return mapper.map(brands, BrandsBean.class);
	}

	@Override
	public BrandsBean updateBrand(BrandsBean brandsBean) {
		Brands brands = mapper.map(brandsBean, Brands.class);
		brandsRepo.save(brands);
		return mapper.map(brands, BrandsBean.class);
	}

	@Override
	public BrandsBean getBrandById(String brandId) {
		Brands brands = brandsRepo.findById(brandId).get();
		return mapper.map(brands, BrandsBean.class);
	}

	@Override
	public List<BrandsBean> getAllBrands() {
		List<BrandsBean> brandBeansList = brandsRepo
				.findAll()
				.stream()
				.map(brand->mapper.map(brand, BrandsBean.class))
				.collect(Collectors.toList());
		return brandBeansList;
	}

	@Override
	public List<BrandsBean> deleteBrands(String brandId) {
		brandsRepo.deleteById(brandId);
		return getAllBrands();
	}

	@Override
	public void clearDb() {
		brandsRepo.deleteAll();
		
	}

}
