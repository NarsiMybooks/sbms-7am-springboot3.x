package com.weshopifyplatform.app.services;

import java.util.List;

import com.weshopifyplatform.app.beans.BrandsBean;

public interface BrandsManagementService {

	BrandsBean createBrand(BrandsBean brandsBean);
	BrandsBean updateBrand(BrandsBean brandsBean);
	BrandsBean getBrandById(String brandId);
	List<BrandsBean> getAllBrands();
	List<BrandsBean> deleteBrands(String brandId);
	void clearDb();
	
}
