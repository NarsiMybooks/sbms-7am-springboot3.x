package com.weshopifyplatform.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weshopifyplatform.app.beans.CustomerBean;
import com.weshopifyplatform.app.service.CustomerManagementService;

@Controller
public class CustomersController {
	
	@Autowired
	private CustomerManagementService customerManagementService;

	@RequestMapping(value = {"/addCustomerPage"})
	public String viewAddCustomerPage(Model model) {
		model.addAttribute("customerBean", new CustomerBean());
		return "add-customers.html";
	}
	
	@RequestMapping(value = {"/addCustomer"})
	public String viewAddCustomerPage(@ModelAttribute("customerBean") CustomerBean customerBean, Model model) {
		System.out.println(customerBean.toString());
		customerBean = customerManagementService.registerCustomer(customerBean);
		boolean isCustomerRegistered = false;
		if(StringUtils.hasText(customerBean.getId())) {
		  List<CustomerBean> customersList = customerManagementService.findAllCustomers();
		  model.addAttribute("listOfCustomers", customersList);
		  isCustomerRegistered = true;
		  model.addAttribute("isCustomerRegistered", isCustomerRegistered);
		}else {
			model.addAttribute("isCustomerRegistered", isCustomerRegistered);
		}
		return "list-customers.html";
	}
	
	@RequestMapping(value = {"/viewCustomersPage"})
	public String viewCustomersPage(Model model) {
		 boolean customersFound = false;
		  List<CustomerBean> customersList = customerManagementService.findAllCustomers();
		  if(!CollectionUtils.isEmpty(customersList)) {
			  model.addAttribute("listOfCustomers", customersList);
			  customersFound = true;
		  }
		  model.addAttribute("customersFound", customersFound);
		  
		  return "list-customers.html";
	}
	
	@RequestMapping(value = {"/deleteCustomer"})
	public String deleteCustomer(@RequestParam("custId") String customerId, Model model) {
		System.out.println("Customer Id is:\t"+customerId);
		boolean customersFound = false;
		List<CustomerBean> customersList = customerManagementService.deleteCustomerById(customerId);
		if(!CollectionUtils.isEmpty(customersList)) {
			  model.addAttribute("listOfCustomers", customersList);
			  customersFound = true;
		  }
		  model.addAttribute("customersFound", customersFound);
		  
		  return "list-customers.html";
	}
	
	@RequestMapping(value = {"/editCustomerPage"})
	public String editCustomerPage(@RequestParam("custId") String customerId,Model model) {
		CustomerBean customerBean = customerManagementService.findCustomerById(customerId);
		System.out.println(customerBean.toString());
		model.addAttribute("customerBean", customerBean);
		return "add-customers.html";
	}
	
}