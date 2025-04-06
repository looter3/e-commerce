package com.ecommerce.kafka.request;

import java.io.IOException;

import com.ecommerce.kafka.request.RequestType.RequestTypeDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.extern.log4j.Log4j2;

/**
 * @author lex_looter
 *
 *         22 mar 2025
 *
 */
@JsonDeserialize(using = RequestTypeDeserializer.class)
public interface RequestType {

	String getValue();

	@Log4j2
	public static class RequestTypeDeserializer extends JsonDeserializer<RequestType> {

		@Override
		public RequestType deserialize(final JsonParser p, final DeserializationContext ctxt)
				throws IOException {
			final String value = p.getText();

			// Try both enums or object singletons
			try {
				if (value.contains("BY_PRODUCT")) {
					return ProductAwareRequest.fromValue(value);
				}
				return SimpleCRUDRequest.fromValue(value);
			} catch (final IllegalArgumentException e) {
				log.error("Request of type {} not found", value);
				return null;
			}
		}
	}

}
