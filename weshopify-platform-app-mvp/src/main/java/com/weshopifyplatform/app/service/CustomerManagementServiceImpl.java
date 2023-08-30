package com.weshopifyplatform.app.service;

import java.util.LinkedHashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.weshopifyplatform.app.beans.AuthenticationBean;
import com.weshopifyplatform.app.beans.CustomerBean;

@Service
public class CustomerManagementServiceImpl implements CustomerManagementService {
	
	private static LinkedHashMap<String, CustomerBean> IN_MEMORY_DB = new LinkedHashMap<>();

	@Override
	public CustomerBean registerCustomer(CustomerBean customerBean) {
		customerBean.setId(UUID.randomUUID().toString());
		IN_MEMORY_DB.put(customerBean.getUserName(), customerBean);
		return customerBean;
	}

	@Override
	public AuthenticationBean authenticateUser(AuthenticationBean authenticationBean) {
		
		CustomerBean customerBean =  IN_MEMORY_DB.get(authenticationBean.getUserName());
		if(customerBean != null && customerBean.getPassword().equals(authenticationBean.getPassword())) {
			authenticationBean.setAuthenticated(true);
		}
		
		return authenticationBean;
	}

}
