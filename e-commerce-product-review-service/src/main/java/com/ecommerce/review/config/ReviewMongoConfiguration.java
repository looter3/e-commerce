package com.ecommerce.review.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ecommerce.common.mongodb.config.AbstractMongoConfiguration;

/**
 * @author Lorenzo Leccese
 *
 *         9 feb 2025
 *
 */
@Configuration
public class ReviewMongoConfiguration extends AbstractMongoConfiguration {

	public ReviewMongoConfiguration(final Environment env) {
		super(env);
	}

	@Override
	protected String getDatabaseName() {
		return env.getProperty("spring.data.mongodb.database", "review-db");
	}

}
