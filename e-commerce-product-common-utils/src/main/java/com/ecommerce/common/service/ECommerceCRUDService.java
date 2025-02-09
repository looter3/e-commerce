package com.ecommerce.common.service;

import com.ecommerce.common.dto.ECommerceDTO;

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
	void deleteECommerceEntity(int id);

	// Read
	DTO getECommerceEntity(int id);

}
