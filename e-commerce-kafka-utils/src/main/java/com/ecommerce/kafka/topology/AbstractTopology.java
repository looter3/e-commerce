package com.ecommerce.kafka.topology;

import static com.ecommerce.kafka.topology.TopicConstants.IN;
import static com.ecommerce.kafka.topology.TopicConstants.OUT;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;

import com.ecommerce.common.service.ECommerceCRUDService;
import com.ecommerce.kafka.config.KafkaTopicConfiguration;
import com.ecommerce.kafka.dto.ECommerceDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import jakarta.annotation.PostConstruct;

/**
 * @author Lorenzo Leccese
 *
 *         9 mar 2025
 *
 */
@RequiredArgsConstructor
@Log4j2
public abstract class AbstractTopology<DTO extends ECommerceDTO, SERV extends ECommerceCRUDService<DTO>> {

	protected final SERV service;
	protected final KafkaTopicConfiguration kafkaTopicConfiguration;

	private String inputTopic;
	private String outputTopic;
	private Class<DTO> dtoClass;

	@PostConstruct
	private void init() {
		this.dtoClass = this.provideDTOClass();
		this.inputTopic = kafkaTopicConfiguration.getApplicationName().concat(IN);
		this.outputTopic = kafkaTopicConfiguration.getApplicationName().concat(OUT);
	}

	@Bean
	KStream<String, String> crudOperationStream(final StreamsBuilder kStreamBuilder) {
		return KStreamProcessor.processCRUD(kStreamBuilder, service, inputTopic, outputTopic, dtoClass);
	}

	protected abstract Class<DTO> provideDTOClass();

	/*-
	// Create
	@Value(value = "${com.ecommerce.topics.create-entity-topic.in}")
	private String createEntityInputTopic;
	@Value(value = "${com.ecommerce.topics.create-entity-topic.out}")
	private String createEntityOutputTopic;

	// Delete
	@Value(value = "${com.ecommerce.topics.delete-entity-topic.in}")
	private String deleteEntityInputTopic;
	@Value(value = "${com.ecommerce.topics.delete-entity-topic.out}")
	private String deleteEntityOutputTopic;

	// Read
	@Value(value = "${com.ecommerce.topics.read-entity-topic.in}")
	private String readEntityInputTopic;
	@Value(value = "${com.ecommerce.topics.read-entity-topic.out}")
	private String readEntityOutputTopic;

	@Bean
	KStream<String, String> createEntityStream(final StreamsBuilder kStreamBuilder) {
		return KStreamProcessor.process(kStreamBuilder, this::onCreateEntity, this::provideDTOClass, createEntityInputTopic, createEntityOutputTopic);
	}
	
	@Bean
	KStream<String, String> deleteEntityStream(final StreamsBuilder kStreamBuilder) {
		return KStreamProcessor.process(kStreamBuilder, this::onDeleteEntity, this::provideDTOClass, deleteEntityInputTopic, deleteEntityOutputTopic);
	}
	
	@Bean
	KStream<String, String> readEntityStream(final StreamsBuilder kStreamBuilder) {
		return KStreamProcessor.process(kStreamBuilder, this::onReadEntity, this::provideDTOClass, readEntityInputTopic, readEntityOutputTopic);
	}

	protected abstract DTO onCreateEntity(DTO dto);

	protected abstract DTO onDeleteEntity(DTO dto);

	protected abstract DTO onReadEntity(DTO dto);

	*/

}
