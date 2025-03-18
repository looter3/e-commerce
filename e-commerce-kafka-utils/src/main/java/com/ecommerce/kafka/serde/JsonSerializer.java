package com.ecommerce.kafka.serde;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.Gson;

/**
 * @author Lorenzo Leccese
 *
 *         21 feb 2025
 *
 */
public class JsonSerializer<T> implements Serializer<T> {

	private final Gson gson = new Gson();

	@Override
	public void configure(final Map<String, ?> map, final boolean b) {

	}

	@Override
	public byte[] serialize(final String topic, final T t) {
		return gson.toJson(t).getBytes(Charset.forName("UTF-8"));
	}

	@Override
	public void close() {

	}

}
