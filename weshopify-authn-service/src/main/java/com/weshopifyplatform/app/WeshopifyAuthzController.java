package com.weshopifyplatform.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authn")
public class WeshopifyAuthzController {

	@GetMapping("/token")
	public void fetchAccessTokenFromIdp() {
		System.out.println("response from autho idp is");
		
	
	}
}
