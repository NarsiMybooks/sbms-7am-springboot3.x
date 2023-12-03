package com.weshopifyplatform.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WeshopifyServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshopifyServiceRegistryApplication.class, args);
	}

}
