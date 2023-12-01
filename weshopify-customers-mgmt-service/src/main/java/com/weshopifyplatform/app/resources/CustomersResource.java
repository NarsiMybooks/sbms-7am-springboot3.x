/**
 * 
 */
package com.weshopifyplatform.app.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weshopifyplatform.app.beans.CustomerBean;
import com.weshopifyplatform.app.service.CustomerManagementService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Controller
@RequestMapping(path = "/api")
@Slf4j
public class CustomersResource {

	@Autowired
	private CustomerManagementService customerManagementService;
	
	@GetMapping(value = {"/customers"})
	public @ResponseBody List<CustomerBean> findAllCustomers(){
		log.info("i am in findAllCustomers Rest Resource Method");
		return customerManagementService.findAllCustomers();
	}
	
	@GetMapping(value = {"/customers/{currPage}/{noOfRecPerPage}"})
	public @ResponseBody List<CustomerBean> findAllCustomersByPaging(@PathVariable("currPage") int currPage,
			@PathVariable("noOfRecPerPage") int noOfRecPerPage){
		log.info("i am in findAllCustomers By Paging,{},{}",currPage,noOfRecPerPage);
		if(currPage != 0) {
			currPage = currPage-1;
		}
		return customerManagementService.findAllCustomers(currPage,noOfRecPerPage);
	}
	
	@GetMapping(path = {"/customers/{customerId}"})
	public @ResponseBody CustomerBean findCustomerById(@PathVariable("customerId") int customerId) {
	  return customerManagementService.findCustomerById(String.valueOf(customerId));
	}
	
	@DeleteMapping(path = {"/customers/{customerId}"})
	public @ResponseBody ResponseEntity<Object> deleteCustomerById(@PathVariable("customerId") int customerId){
		Map<String,String> respData = new HashMap<>();
		CustomerBean customerBean = customerManagementService.findCustomerById(String.valueOf(customerId));
		if(customerBean == null) {
			respData.put("message", "Customer "+customerId+" Not Found");
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("message", "Customer "+customerId+" Not Found");
			return new ResponseEntity<Object>(respData,responseHeaders,HttpStatus.NO_CONTENT);
		}else {
			List<CustomerBean> customerListAfterDeletion = customerManagementService.deleteCustomerById(String.valueOf(customerId));
			if(customerListAfterDeletion != null && customerListAfterDeletion.size() >0) {
				CustomerBean dbCustomerBean = customerManagementService.findCustomerById(String.valueOf(customerId));
				if(dbCustomerBean == null) {
					return new ResponseEntity<Object>(customerListAfterDeletion,HttpStatus.ACCEPTED);
				}else {
					respData.put("message", "Customer "+customerBean.getUserName()+" Not deleted");
					return new ResponseEntity<Object>(respData,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else {
				respData.put("message", "Customer "+customerId+" Not Found");
				return new ResponseEntity<Object>(respData,HttpStatus.NO_CONTENT);
				
			}
			
		}
	}
	
	@PostMapping(path = {"/customers"})
	public ResponseEntity<Object> createCustomer(@RequestBody CustomerBean customerBean) {
		log.info("Create Customer Method,{}",customerBean.toString());
		customerBean = customerManagementService.registerCustomer(customerBean);
		Map<String,String> respData = new HashMap<>();
		if(customerBean != null && Integer.valueOf(customerBean.getId()) >0) {
			return new ResponseEntity<Object>(customerBean, HttpStatus.CREATED);
		}else if(customerBean != null && Integer.valueOf(customerBean.getId())==0){
			respData.put("message", "Customer Not Created.. Please Try again");
			return new ResponseEntity<Object>(respData, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			respData.put("message", "Customer Not Created..Something wrong with the Input data. "
					+ "Please Correct it and  Please Try again");
			return new ResponseEntity<Object>(respData, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path = {"/customers"})
	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerBean customerBean) {
		log.info("updateCustomer,{}",customerBean.toString());
		Map<String,String> respData = new HashMap<>();
		
		if(customerBean != null && customerBean.getId() == null) {
			respData.put("message", "Customer trying to update with the insufficient data");
			return new ResponseEntity<Object>(respData, HttpStatus.BAD_REQUEST);
		}else {
			customerBean = customerManagementService.registerCustomer(customerBean);
			if(Integer.valueOf(customerBean.getId()) >0) {
				return new ResponseEntity<Object>(customerBean, HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<Object>(customerBean, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
