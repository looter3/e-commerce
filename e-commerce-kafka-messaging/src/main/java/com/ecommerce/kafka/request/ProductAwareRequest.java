package com.ecommerce.kafka.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lex_looter
 *
 *         6 apr 2025
 *
 */
public class ProductAwareRequest implements RequestType {

	private final String value;

	public static final ProductAwareRequest CREATE_BY_PRODUCT_ID = new ProductAwareRequest("CREATE_BY_PRODUCT_ID");
	public static final ProductAwareRequest READ_BY_PRODUCT_ID = new ProductAwareRequest("READ_BY_PRODUCT_ID");
	public static final ProductAwareRequest UPDATE_BY_PRODUCT_ID = new ProductAwareRequest("UPDATE_BY_PRODUCT_ID");
	public static final ProductAwareRequest DELETE_BY_PRODUCT_ID = new ProductAwareRequest("DELETE_BY_PRODUCT_ID");

	private ProductAwareRequest(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public static ProductAwareRequest fromValue(final String value) {
		return switch (value.toUpperCase()) {
			case "CREATE_BY_PRODUCT_ID" -> CREATE_BY_PRODUCT_ID;
			case "READ_BY_PRODUCT_ID" -> READ_BY_PRODUCT_ID;
			case "UPDATE_BY_PRODUCT_ID" -> UPDATE_BY_PRODUCT_ID;
			case "DELETE_BY_PRODUCT_ID" -> DELETE_BY_PRODUCT_ID;
			default -> throw new IllegalArgumentException("Unknown CRUDRequest type: " + value);
		};
	}

}
