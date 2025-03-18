package com.ecommerce.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Lorenzo Leccese
 *
 *         8 mar 2025
 *
 */
@Configuration
public class KafkaTopicConfiguration {

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

}
