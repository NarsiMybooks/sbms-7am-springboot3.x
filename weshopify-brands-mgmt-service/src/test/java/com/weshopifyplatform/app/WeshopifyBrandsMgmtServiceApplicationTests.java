package com.weshopifyplatform.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weshopifyplatform.app.outbound.CategoriesOutboundCommunicator;

@SpringBootTest
class WeshopifyBrandsMgmtServiceApplicationTests {

	@Autowired
	private CategoriesOutboundCommunicator categories;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCagetoriesById() {
		String respData = categories.getCategoryById(1);
		assertNotNull(respData);
		System.out.println(respData);
	}

}
