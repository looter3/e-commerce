package com.ecommerce.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         9 feb 2025
 *
 */
@Configuration
//@Profile("!docker")
@RequiredArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

	private final Environment env;

	@Override
	protected String getDatabaseName() {
		return env.getProperty("spring.data.mongodb.database", "product-db");
	}

	@Override
	public boolean autoIndexCreation() {
		return true;
	}

	@Override
	@Bean
	public MongoClient mongoClient() {
		final String host = env.getProperty("spring.data.mongodb.host", "localhost");
		final int port = Integer.parseInt(env.getProperty("spring.data.mongodb.port", "27017"));
		return MongoClients.create(String.format("mongodb://%s:%d", host, port));
	}

}
