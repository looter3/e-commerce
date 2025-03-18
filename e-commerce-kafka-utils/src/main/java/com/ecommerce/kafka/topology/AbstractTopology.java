package com.ecommerce.kafka.topology;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.ecommerce.common.dto.ECommerceDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author Lorenzo Leccese
 *
 *         9 mar 2025
 *
 */
@RequiredArgsConstructor
@Log4j2
public abstract class AbstractTopology<DTO extends ECommerceDTO> {

	@Value(value = "${com.ecommerce.topics.create-entity-topic.in}")
	private String createEntityInputTopic;
	@Value(value = "${com.ecommerce.topics.create-entity-topic.out}")
	private String createEntityOutputTopic;

	@Bean
	KStream<String, String> createEntityStream(final StreamsBuilder kStreamBuilder) {
		return KStreamProcessor.process(kStreamBuilder, this::onCreateEntity, this::provideDTOClass, createEntityInputTopic, createEntityOutputTopic);
	}

	protected abstract DTO onCreateEntity(DTO dto);

	protected abstract Class<DTO> provideDTOClass();

}
