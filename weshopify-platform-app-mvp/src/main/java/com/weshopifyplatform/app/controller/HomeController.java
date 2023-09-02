package com.weshopifyplatform.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weshopifyplatform.app.beans.AuthenticationBean;
import com.weshopifyplatform.app.beans.CustomerBean;
import com.weshopifyplatform.app.service.CustomerManagementService;

@Controller
public class HomeController {

	@Autowired
	private CustomerManagementService customerService;
	
	@RequestMapping(value = {"/","/home","/loginPage"})
	public String viewHomePage(Model model) {	
		model.addAttribute("authnBean", new AuthenticationBean());
		return "home.html";
	}
	
	@RequestMapping(value = {"/login"})
	public String doLogin(@ModelAttribute("authnBean") AuthenticationBean authenticationBean, Model model) {
		System.out.println(authenticationBean.toString());
		 authenticationBean = customerService.authenticateUser(authenticationBean);
		 if(authenticationBean.isAuthenticated()) {
			 boolean customersFound = false;
			  List<CustomerBean> customersList = customerService.findAllCustomers();
			  if(!CollectionUtils.isEmpty(customersList)) {
				  model.addAttribute("listOfCustomers", customersList);
				  customersFound = true;
			  }
			model.addAttribute("customersFound", customersFound);
			  
			  
			 return "dashboard.html"; 
		 }else {
			 String message = "Bad Credentials!! Please Try agin with the proper UserName and Password";
			 model.addAttribute("message", message);
			 return "home.html";
		 }
		
	}
	
	@RequestMapping(value = {"/signupPage"})
	public String viewSignupPage(Model model) {
		model.addAttribute("customerBean", new CustomerBean());
		return "sign-up.html";
	}
	
	@RequestMapping(value = {"/signup"})
	public String handleSignupData(@ModelAttribute("customerBean") CustomerBean customerBean,Model model) {
		System.out.println(customerBean.toString());
		customerBean =  customerService.registerCustomer(customerBean);
		boolean isRegistered = false;
		if(customerBean != null && customerBean.getId() != null) {
			System.out.println("Customer Regustered Successfully");
			isRegistered = true;
			
		}
		model.addAttribute("isRegistered", isRegistered);
		return "sign-up.html";
	}
}
