package com.ecommerce.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ECommerceDiscoveryServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ECommerceDiscoveryServiceApplication.class, args);
	}

}
