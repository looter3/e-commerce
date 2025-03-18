package com.ecommerce.common.service;

import com.ecommerce.common.dto.ECommerceDTO;

import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
public interface ECommerceCRUDService<DTO extends ECommerceDTO> {

	// Create
	DTO createECommerceEntity(DTO dto);

	// Delete
	Mono<Void> deleteECommerceEntity(int id);

	// Read
	Mono<DTO> getECommerceEntity(int id);

}
