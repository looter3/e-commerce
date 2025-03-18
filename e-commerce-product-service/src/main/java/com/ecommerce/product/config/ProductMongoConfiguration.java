package com.ecommerce.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ecommerce.common.config.AbstractMongoConfiguration;

/**
 * @author Lorenzo Leccese
 *
 *         9 feb 2025
 *
 */
@Configuration
public class ProductMongoConfiguration extends AbstractMongoConfiguration {

	public ProductMongoConfiguration(final Environment env) {
		super(env);
	}

	@Override
	protected String getDatabaseName() {
		return env.getProperty("spring.data.mongodb.database", "product-db");
	}
}
