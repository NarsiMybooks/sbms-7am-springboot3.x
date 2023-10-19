package com.weshopifyplatform.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.weshopifyplatform.app.beans.AuthenticationBean;
import com.weshopifyplatform.app.beans.CustomerBean;
import com.weshopifyplatform.app.controller.HomeController;
import com.weshopifyplatform.app.service.CustomerManagementService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WeshopifyPlatformAppMvpApplication.class)
@ActiveProfiles(profiles = "qa")
public class UserManagementTestWithMock {

	@Mock
	private CustomerManagementService customerManagementService;
	
	@InjectMocks
	private HomeController homeController;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
		assertNotNull(mockMvc);
	}
	
	@Test
	@Order(value = 1)
	public void testViewSignupPage() throws Exception {
		mockMvc
		.perform(get("/signupPage"))
		.andExpect(model().attribute("customerBean", new CustomerBean()))
		.andExpect(view().name("sign-up.html"));				
	}
	
	@Test
	@Order(value = 2)
	public void testUserRegistration() throws Exception {
		int randomId = new Random().nextInt();
		
		CustomerBean customerBean = new CustomerBean();
		customerBean.setFirstName("testCustomer");
		customerBean.setLastName("test");
		customerBean.setEmail("testCustomer@yopmail.com");
		customerBean.setPassword("testCustomer@123");
		customerBean.setUserName("testCustomer");
		customerBean.setMobile("9876543210");
		customerBean.setId(String.valueOf(randomId));
		
		when(customerManagementService.registerCustomer(customerBean))
		.thenReturn(customerBean);
		
		mockMvc.perform(post("/signup", new CustomerBean())
				.param("firstName", "testCustomer")
				.param("lastName", "test")
				.param("email", "testCustomer@yopmail.com")
				.param("password", "testCustomer@123")
				.param("userName", "testCustomer")
				.param("mobile", "9876543210")
				.param("id", String.valueOf(randomId))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(model().attribute("isRegistered", true))
				.andExpect(view().name("sign-up.html"));
		
		verify(customerManagementService,Mockito.times(1)).registerCustomer(customerBean);	
	}	
	
	@Test
	@Order(value = 3)
	public void testViewHomePage() throws Exception {
		mockMvc
		.perform(get("/loginPage"))
		.andExpect(model().attribute("authnBean", new AuthenticationBean()))
		.andExpect(view().name("home.html"));
	}
	
	@Test
	@Order(value = 4)
	public void testDoLoginPositive() throws Exception {
		
		AuthenticationBean authnBean = new AuthenticationBean();
		authnBean.setAuthenticated(true);
		authnBean.setUserName("testCustomer");
		authnBean.setPassword("testCustomer@123");
		
		when(customerManagementService.authenticateUser(authnBean))
		.thenReturn(authnBean);
		
		mockMvc
		.perform(post("/login", new AuthenticationBean())
				.param("userName", "testCustomer")
				.param("password", "testCustomer@123")
				.param("isAuthenticated","true")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(model().attribute("customersFound", true))
				.andExpect(view().name("dashboard.html"));
		
	  verify(customerManagementService, Mockito.times(1)).authenticateUser(authnBean);
	}
}
