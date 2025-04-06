package com.ecommerce.common.service;

import com.ecommerce.kafka.dto.ECommerceDTO;

import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
public interface ECommerceCRUDService<DTO extends ECommerceDTO> {

//	ECommerceKafkaRequest crudOperation(ECommerceKafkaRequest request);

	// Create
	DTO createECommerceEntity(DTO dto);

	// Delete
	void deleteECommerceEntity(int id);

	// Read
	Mono<DTO> getECommerceEntity(int id);

}
