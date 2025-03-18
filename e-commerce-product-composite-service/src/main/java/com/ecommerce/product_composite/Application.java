package com.ecommerce.product_composite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
@ComponentScan(basePackages = "com.ecommerce")
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
