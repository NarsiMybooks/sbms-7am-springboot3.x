package com.weshopifyplatform.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.weshopifyplatform.app.beans.AuthenticationBean;
import com.weshopifyplatform.app.beans.CustomerBean;
import com.weshopifyplatform.app.models.Customer;
import com.weshopifyplatform.app.repos.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerManagementServiceImpl implements CustomerManagementService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public CustomerBean registerCustomer(CustomerBean customerBean) {
		log.info("i am registerCustomer method:\t"+customerBean.toString());
		if(!StringUtils.hasText(customerBean.getRole())) {
			customerBean.setRole("user");
		}
		Customer customer = mapBeanToEntity(customerBean);
		customerRepo.save(customer);
		log.info("Customer Registered Successfully"+customer.getId());
		customerBean = mapEntityToBean(customer);
		return customerBean;
	}

	@Override
	public AuthenticationBean authenticateUser(AuthenticationBean authenticationBean) {
		
		Customer customer = customerRepo.findCustomerByUserName(authenticationBean.getUserName());
		if(customer != null && customer.getPassword().equals(authenticationBean.getPassword())) {
			authenticationBean.setAuthenticated(true);
		}
		
		return authenticationBean;
	}

	@Override
	public List<CustomerBean> findAllCustomers() {
		// TODO Auto-generated method stub
		List<CustomerBean> customersBeanList = new ArrayList<>();
		Iterable<Customer> customersList = customerRepo.findAll();
		customersList.forEach(customerEntity->{
			CustomerBean customerBean = mapEntityToBean(customerEntity);
			customersBeanList.add(customerBean);
		});
		return customersBeanList;
	}

	@Override
	public List<CustomerBean> deleteCustomerById(String custId) {
		customerRepo.deleteById(Integer.valueOf(custId));
		return findAllCustomers();
	}

	@Override
	public CustomerBean findCustomerById(String customerId) {
		Customer customer = customerRepo.findById(Integer.valueOf(customerId)).get();
		return mapEntityToBean(customer);
	}
	
	private Customer mapBeanToEntity(CustomerBean customerBean) {
		Customer customer = new Customer();
		if(customerBean.getId() != null) {
			customer.setId(Integer.valueOf(customerBean.getId()));
		}
		customer.setFirstName(customerBean.getFirstName());
		customer.setLastName(customerBean.getLastName());
		customer.setEmail(customerBean.getEmail());
		customer.setPassword(customerBean.getPassword());
		customer.setMobile(customerBean.getMobile());
		customer.setUserName(customerBean.getUserName());
		
		return customer;
	}
	
	private CustomerBean mapEntityToBean(Customer customer) {
		CustomerBean customerBean = new CustomerBean();
		customerBean.setFirstName(customer.getFirstName());
		customerBean.setLastName(customer.getLastName());
		customerBean.setEmail(customer.getEmail());
		customerBean.setPassword(customer.getPassword());
		customerBean.setMobile(customer.getMobile());
		customerBean.setUserName(customer.getUserName());
		customerBean.setId(String.valueOf(customer.getId()));
	
		return customerBean;
	}

	@Override
	public List<CustomerBean> findAllCustomers(int currPage, int noOfRecPerPage) {
		PageRequest page = PageRequest.of(currPage, noOfRecPerPage);
		Sort.by(Direction.DESC, "email");
		Page<Customer> customerPage = customerRepo.findAll(page);
		List<Customer> customersList = customerPage.getContent();
		List<CustomerBean> customersBeanList = new ArrayList<>();
		customersList.forEach(customerEntity->{
			CustomerBean customerBean = mapEntityToBean(customerEntity);
			customersBeanList.add(customerBean);
		});
		return customersBeanList;
	}

}
