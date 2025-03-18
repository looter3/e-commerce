package com.ecommerce.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         16 feb 2025
 *
 */
@RequiredArgsConstructor
public abstract class AbstractMongoConfiguration {

	protected final Environment env;

	protected abstract String getDatabaseName();

	@Bean
	MongoClient mongoClient() {
		final String host = env.getProperty("spring.data.mongodb.host", "localhost");
		final int port = Integer.parseInt(env.getProperty("spring.data.mongodb.port", "27017"));
		return MongoClients.create(String.format("mongodb://%s:%d", host, port));
	}

	@Bean
	ReactiveMongoTemplate reactiveMongoTemplate(final MongoClient mongoClient) {
		return new ReactiveMongoTemplate(mongoClient, getDatabaseName()); // Uses dynamic DB name
	}

}
