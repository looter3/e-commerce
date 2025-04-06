package com.ecommerce.kafka.topology;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.ecommerce.common.service.ECommerceCRUDService;
import com.ecommerce.common.service.ProductAwareService;
import com.ecommerce.kafka.dto.ECommerceDTO;
import com.ecommerce.kafka.request.ECommerceKafkaRequest;
import com.ecommerce.kafka.request.ProductAwareRequest;
import com.ecommerce.kafka.request.RequestType;
import com.ecommerce.kafka.request.SimpleCRUDRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

/**
 * @author Lorenzo Leccese
 *
 *         9 mar 2025
 *
 */
@SuppressWarnings("rawtypes")
@Log4j2
public class KStreamProcessor {

	// TODO MOVE OUTSIDE
	@FunctionalInterface
	public interface BiFunction<T, U, R> {
		R apply(T t, U u);
	}

	@FunctionalInterface
	public interface TriFunction<T, U, V, R> {
		R apply(T t, U u, V v);
	}

	private static final ObjectMapper objectMapper = new ObjectMapper();

	static final Map<RequestType, TriFunction<? extends ECommerceCRUDService, Object, Class<?>, Optional<?>>> actionMap;

	static {
		actionMap = Map.of(
				SimpleCRUDRequest.CREATE,
				(TriFunction<ECommerceCRUDService<ECommerceDTO>, Object, Class<?>, Optional<?>>) (service, dto, clazz) -> {
					final Object convertValue = objectMapper.convertValue(dto, clazz);
					return Optional.of(service.createECommerceEntity((ECommerceDTO) convertValue));
				},

				SimpleCRUDRequest.DELETE,
				(TriFunction<ECommerceCRUDService<ECommerceDTO>, Object, Class<?>, Optional<?>>) (service, id, clazz) -> {
					service.deleteECommerceEntity((int) id);
					return Optional.empty();
				},

				ProductAwareRequest.DELETE_BY_PRODUCT_ID,
				(TriFunction<ECommerceCRUDService<ECommerceDTO>, Object, Class<?>, Optional<?>>) (service, id, clazz) -> {
					((ProductAwareService) service).deleteByProductId((int) id);
					return Optional.empty();
				});
	}

	@SuppressWarnings("unchecked")
	public static <DTO extends ECommerceDTO, SERV extends ECommerceCRUDService<DTO>> KStream<String, String> processCRUD(
			final StreamsBuilder kStreamBuilder,
			final SERV service,
			final String inputTopic,
			final String outputTopic,
			final Class<DTO> dtoClass) {

		final JsonSerde<ECommerceKafkaRequest> jsonSerde = new JsonSerde<>(ECommerceKafkaRequest.class);
		jsonSerde.configure(Collections.singletonMap(JsonDeserializer.TRUSTED_PACKAGES, "*"), false);
		log.info("Subscribing to topic: {}", inputTopic);

		final KStream<String, ECommerceKafkaRequest> stream = kStreamBuilder.stream(inputTopic,
				Consumed.with(Serdes.String(), jsonSerde));

		log.info("Initializing stream for {}", inputTopic);

		stream
			.mapValues(request -> {
				final TriFunction<SERV, Object, Class<?>, Optional<?>> handler = (TriFunction<SERV, Object, Class<?>, Optional<?>>) actionMap.get(request.getRequestType());

				final Optional<?> result = handler.apply(service, request.getDataToSend(), dtoClass);

				if (result.isPresent()) {
					try {
						return objectMapper.writeValueAsString(result.get());
					} catch (final Exception e) {
						throw new RuntimeException("Failed to serialize result", e);
					}
				}

				return "COMPLETED";
			})
			.to(outputTopic, Produced.with(Serdes.String(), Serdes.String()));

		return stream.mapValues(value -> "COMPLETED");
	}

//	private static final Map<CRUDRequest, Function<ECommerceKafkaRequest<CRUDRequest>, ECommerceKafkaRequest<CRUDRequest>>> crudOperations;

//	static {
//		crudOperations = Map.of(CRUDRequest.CREATE, ECommerceCRUDService::createECommerceEntity);
//	}

	/*-
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
	*/

}
