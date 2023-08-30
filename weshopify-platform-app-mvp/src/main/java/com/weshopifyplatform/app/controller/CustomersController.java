package com.weshopifyplatform.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weshopifyplatform.app.beans.CustomerBean;

@Controller
public class CustomersController {

	@RequestMapping(value = {"/addCustomerPage"})
	public String viewAddCustomerPage(Model model) {
		model.addAttribute("customerBean", new CustomerBean());
		return "add-customers.html";
	}
	
	@RequestMapping(value = {"/addCustomer"})
	public String viewAddCustomerPage(@ModelAttribute("customerBean") CustomerBean customerBean, Model model) {
		System.out.println(customerBean.toString());
		return "add-customers.html";
	}
}
