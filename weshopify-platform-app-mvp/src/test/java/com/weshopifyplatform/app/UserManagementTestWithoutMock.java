package com.weshopifyplatform.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.weshopifyplatform.app.beans.AuthenticationBean;
import com.weshopifyplatform.app.beans.CustomerBean;
import com.weshopifyplatform.app.service.CustomerManagementService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "qa")
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
public class UserManagementTestWithoutMock {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		log.info("In Setup Method....");
		assertNotNull(webApplicationContext);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		assertNotNull(mockMvc);
		log.info("Setup done...");
	}

	@Test
	public void testConfig() {
		log.info("test Config Setup..");
		CustomerManagementService customerService = Mockito.mock(CustomerManagementService.class);
		int randomId = new Random().nextInt();
		CustomerBean customerBean = new CustomerBean();
		customerBean.setFirstName("testCustomer");
		customerBean.setLastName("test");
		customerBean.setEmail("testCustomer@yopmail.com");
		customerBean.setPassword("testCustomer@123");
		customerBean.setUserName("testCustomer");
		customerBean.setMobile("9876543210");
		customerBean.setId(String.valueOf(randomId));

		when(customerService.registerCustomer(customerBean)).thenReturn(customerBean);
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
		log.info("start testing testUserRegistration...");
		int randomId = new Random().nextInt();
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
		mockMvc
		.perform(post("/login", new AuthenticationBean())
				.param("userName", "testCustomer")
				.param("password", "testCustomer@123")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(model().attribute("customersFound", true))
				.andExpect(view().name("dashboard.html"));
	}
	
	@Test
	@Order(value = 4)
	public void testDoLoginNegative() throws Exception {
		String message = "Bad Credentials!! Please Try agin with the proper UserName and Password";
		mockMvc
		.perform(post("/login", new AuthenticationBean())
				.param("userName", "testCustomer")
				.param("password", "testCustomer")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(model().attribute("message", message))
				.andExpect(view().name("home.html"));
	}

}
