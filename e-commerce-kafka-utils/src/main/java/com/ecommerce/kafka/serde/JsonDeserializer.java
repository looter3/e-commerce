package com.ecommerce.kafka.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;

/**
 * @author Lorenzo Leccese
 *
 *         21 feb 2025
 *
 */
public class JsonDeserializer<T> implements Deserializer<T> {

	private final Gson gson = new Gson();
	private Class<T> deserializedClass;

	public JsonDeserializer(final Class<T> deserializedClass) {
		this.deserializedClass = deserializedClass;
	}

	public JsonDeserializer() {}

	@Override
	@SuppressWarnings("unchecked")
	public void configure(final Map<String, ?> map, final boolean b) {
		if (deserializedClass == null) {
			deserializedClass = (Class<T>) map.get("serializedClass");
		}
	}

	@Override
	public T deserialize(final String s, final byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		return gson.fromJson(new String(bytes), deserializedClass);

	}

	@Override
	public void close() {

	}

}
