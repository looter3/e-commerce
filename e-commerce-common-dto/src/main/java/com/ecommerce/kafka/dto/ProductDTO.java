package com.ecommerce.kafka.dto;

import lombok.NoArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         1 feb 2025
 *
 */
@NoArgsConstructor
public class ProductDTO extends ECommerceDTO {

	private int productId;
	private String name;
	private int weight;

	@Override
	public int getObjectId() {
		return this.productId;
	}

	public ProductDTO(final int productId, final String name, final int weight) {
		this.productId = productId;
		this.name = name;
		this.weight = weight;
	}

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

}
