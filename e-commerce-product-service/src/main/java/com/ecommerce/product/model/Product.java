package com.ecommerce.product.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecommerce.common.mongodb.entity.ECommerceEntity;

/**
 * @author Lorenzo Leccese
 *
 *         4 feb 2025
 *
 */
@Document
public class Product extends ECommerceEntity {

	@Indexed(unique = true)
	private int productId;

	private String name;
	private int weight;

	public int getProductId() {
		return productId;
	}

	public void setProductId(final int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(final int weight) {
		this.weight = weight;
	}

	@Override
	public int provideEntityId() {
		return this.productId;
	}

}
