package com.ecommerce.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.ecommerce.kafka.dto.ECommerceDTO;

import lombok.Data;

@Data
@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaConfiguration<DTO extends ECommerceDTO> {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapAddress;

	@Value(value = "${spring.kafka.streams.application-id}")
	private String applicationId;

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	KafkaStreamsConfiguration kStreamsConfig() {
		final Map<String, Object> props = new HashMap<>();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class.getName());

		return new KafkaStreamsConfiguration(props);
	}

	@Bean
	Map<String, Object> producerConfig() {
		final Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return config;
	}

	@Bean
	Map<String, Object> consumerConfig() {
		final Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return config;
	}

	@Bean
	KafkaTemplate<String, DTO> kafkaTemplate() {
		return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfig()));
	}

	@Bean
	ProducerFactory<String, DTO> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}

	@Bean
	ConsumerFactory<String, DTO> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}

}
