package com.ecommerce.kafka.topology;

import java.util.Collections;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.ecommerce.common.dto.ECommerceDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @author Lorenzo Leccese
 *
 *         9 mar 2025
 *
 */
@Log4j2
public class KStreamProcessor {

	public static <DTO extends ECommerceDTO> KStream<String, String> process(final StreamsBuilder kStreamBuilder, final Function<DTO, DTO> process,
			final Supplier<Class<DTO>> dtoClass,
			final String inputTopic,
			final String outputTopic) {
		final JsonSerde<DTO> jsonSerde = new JsonSerde<>(dtoClass.get());
		jsonSerde.configure(Collections.singletonMap(JsonDeserializer.TRUSTED_PACKAGES, "*"), false);
		log.info("Subscribing to topic: {}", inputTopic);

		final KStream<String, DTO> stream = kStreamBuilder.stream(inputTopic,
				Consumed.with(Serdes.String(), jsonSerde));

		log.info("Initializing stream for {}", inputTopic);

		stream.mapValues(process::apply).to(outputTopic);

		return stream.mapValues(value -> "COMPLETED");
	}

}
