package com.ecommerce.kafka.config;

import static com.ecommerce.kafka.topology.TopicConstants.IN;
import static com.ecommerce.kafka.topology.TopicConstants.OUT;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.Data;

/**
 * @author Lorenzo Leccese
 *
 *         8 mar 2025
 *
 */
@Configuration
@Data
public class KafkaTopicConfiguration {

	@Value(value = "${spring.application.name}")
	private String applicationName;

	@Bean
	NewTopic inputTopic() {
		return TopicBuilder.name(applicationName.concat(IN))
			.build();
	}

	@Bean
	NewTopic outputTopic() {
		return TopicBuilder.name(applicationName.concat(OUT))
			.build();
	}

	/*-
	
	@Value(value = "${com.ecommerce.topics.create-entity-topic.in}")
	private String createEntityInputTopic;
	@Value(value = "${com.ecommerce.topics.create-entity-topic.out}")
	private String createEntityOutputTopic;
	
	@Bean
	NewTopic createEntityInTopic() {
		return TopicBuilder.name(createEntityInputTopic)
			.build();
	}
	
	@Bean
	NewTopic createEntityOutTopic() {
		return TopicBuilder.name(createEntityOutputTopic)
			.build();
	}
	*/
}
