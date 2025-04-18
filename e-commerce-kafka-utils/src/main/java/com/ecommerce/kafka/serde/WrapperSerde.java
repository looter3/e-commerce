package com.ecommerce.kafka.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author Lorenzo Leccese
 *
 *         21 feb 2025
 *
 */
public class WrapperSerde<T> implements Serde<T> {

	final private Serializer<T> serializer;
	final private Deserializer<T> deserializer;

	public WrapperSerde(final Serializer<T> serializer, final Deserializer<T> deserializer) {
		this.serializer = serializer;
		this.deserializer = deserializer;
	}

	@Override
	public void configure(final Map<String, ?> configs, final boolean isKey) {
		serializer.configure(configs, isKey);
		deserializer.configure(configs, isKey);
	}

	@Override
	public void close() {
		serializer.close();
		deserializer.close();
	}

	@Override
	public Serializer<T> serializer() {
		return serializer;
	}

	@Override
	public Deserializer<T> deserializer() {
		return deserializer;
	}

}
