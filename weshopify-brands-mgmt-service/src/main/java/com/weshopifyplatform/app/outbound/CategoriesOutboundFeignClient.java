package com.weshopifyplatform.app.outbound;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.weshopifyplatform.app.beans.CategoriesBean;

//@FeignClient(name = "categories-feign-client",url = "localhost:5052")
//@FeignClient(name = "weshopify-categories-mgmt-service")
@FeignClient(name = "WESHOPIFY-PLATFORM-GATEWAY")
public interface CategoriesOutboundFeignClient {

	@GetMapping(path = "/spg-categories/api/categories/{categoryId}")
	public CategoriesBean getCategoryById(@PathVariable("categoryId") int categoryId);
}
