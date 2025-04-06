package com.ecommerce.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@ComponentScan(basePackages = "com.ecommerce")
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
