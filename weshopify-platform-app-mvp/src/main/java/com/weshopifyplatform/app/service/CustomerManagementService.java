package com.weshopifyplatform.app.service;

import java.util.List;

import com.weshopifyplatform.app.beans.AuthenticationBean;
import com.weshopifyplatform.app.beans.CustomerBean;

public interface CustomerManagementService {

	CustomerBean registerCustomer(CustomerBean customerBean);
	AuthenticationBean authenticateUser(AuthenticationBean authenticationBean);
	List<CustomerBean> findAllCustomers();
	List<CustomerBean> findAllCustomers(int currPage, int noOfRecPerPage);
	
	List<CustomerBean> deleteCustomerById(String custId);
	CustomerBean findCustomerById(String customerId);
	
}
