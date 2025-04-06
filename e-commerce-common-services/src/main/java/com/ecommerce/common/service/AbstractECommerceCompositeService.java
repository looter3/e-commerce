package com.ecommerce.common.service;

import com.ecommerce.kafka.request.ECommerceKafkaRequest;
import org.springframework.kafka.core.KafkaTemplate;

import com.ecommerce.kafka.dto.ECommerceDTO;

import lombok.Data;

/**
 * @author lex_looter
 *
 *         22 mar 2025
 *
 */
@Data
public abstract class AbstractECommerceCompositeService<DTO extends ECommerceDTO>
		implements ECommerceCRUDService<DTO>
/* <REQT extends RequestType> */ {

	protected final KafkaTemplate<String, ECommerceKafkaRequest> kafkaTemplate;

	public AbstractECommerceCompositeService(final KafkaTemplate<String, ECommerceKafkaRequest> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

}
