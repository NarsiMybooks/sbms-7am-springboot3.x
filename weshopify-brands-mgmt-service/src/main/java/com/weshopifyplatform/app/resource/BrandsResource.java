package com.weshopifyplatform.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weshopifyplatform.app.beans.BrandsBean;
import com.weshopifyplatform.app.services.BrandsManagementService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class BrandsResource {

	@Autowired
	private BrandsManagementService brandsManagementService;
	
	@PostMapping(path = {"/brands"})
	public ResponseEntity<BrandsBean> createBrands(@RequestBody BrandsBean brandsBean){
		return new ResponseEntity<BrandsBean>(brandsManagementService.createBrand(brandsBean), 
				HttpStatus.CREATED);
	}
	
	@PutMapping(path = {"/brands"})
	public ResponseEntity<BrandsBean> updateBrands(@RequestBody BrandsBean brandsBean){
		return new ResponseEntity<BrandsBean>(brandsManagementService.createBrand(brandsBean), 
				HttpStatus.CREATED);
	}
	
	@GetMapping(path = {"/brands"})
	public ResponseEntity<List<BrandsBean>> findAllBrands(){
		return new ResponseEntity<List<BrandsBean>>(brandsManagementService.getAllBrands(), 
				HttpStatus.OK);
	}
	
	@GetMapping(path = {"/brands/{brandsId}"})
	public ResponseEntity<BrandsBean> findBrandsById(@PathVariable("brandsId") String id){
		log.info("Brand id is:\t"+id);
		return new ResponseEntity<BrandsBean>(brandsManagementService.getBrandById(id), 
				HttpStatus.OK);
	}
	
	@DeleteMapping(path = {"/brands/{brandsId}"})
	public ResponseEntity<List<BrandsBean>> deleteBrandsById(@PathVariable("brandsId") String id){
		return new ResponseEntity<List<BrandsBean>>(brandsManagementService.deleteBrands(id), 
				HttpStatus.OK);
	}
	
	@DeleteMapping(path = {"/brands/clearDb"})
	public ResponseEntity<List<BrandsBean>>  deleteAllBrands(){
		brandsManagementService.clearDb();
		return new ResponseEntity<List<BrandsBean>>(brandsManagementService.getAllBrands(), 
				HttpStatus.OK);
	}
	
	
}
