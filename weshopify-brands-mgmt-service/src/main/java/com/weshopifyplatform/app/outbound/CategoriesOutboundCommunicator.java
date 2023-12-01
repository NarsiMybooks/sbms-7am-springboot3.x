package com.weshopifyplatform.app.outbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CategoriesOutboundCommunicator {

	@Autowired
	private RestTemplate restTemplate;
	
	public String getCategoryById(int catgoryId) {
		
		String respData = null;
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:5052/api/categories/"+catgoryId, String.class);
		if(response != null && HttpStatus.OK.equals(response.getStatusCode())) {
			respData = response.getBody();
		}
		return respData;
	}
	
}
