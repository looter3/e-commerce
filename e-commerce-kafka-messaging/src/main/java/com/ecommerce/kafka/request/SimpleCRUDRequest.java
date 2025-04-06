package com.ecommerce.kafka.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class SimpleCRUDRequest implements RequestType {

	private final String value;

	public static final SimpleCRUDRequest CREATE = new SimpleCRUDRequest("CREATE");
	public static final SimpleCRUDRequest READ = new SimpleCRUDRequest("READ");
	public static final SimpleCRUDRequest UPDATE = new SimpleCRUDRequest("UPDATE");
	public static final SimpleCRUDRequest DELETE = new SimpleCRUDRequest("DELETE");

	private SimpleCRUDRequest(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public static SimpleCRUDRequest fromValue(final String value) {
		return switch (value.toUpperCase()) {
			case "CREATE" -> CREATE;
			case "READ" -> READ;
			case "UPDATE" -> UPDATE;
			case "DELETE" -> DELETE;
			default -> throw new IllegalArgumentException("Unknown CRUDRequest type: " + value);
		};
	}
}
