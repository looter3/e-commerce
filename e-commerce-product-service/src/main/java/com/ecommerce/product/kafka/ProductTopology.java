package com.ecommerce.product.kafka;

import org.springframework.stereotype.Component;

import com.ecommerce.common.dto.ProductDTO;
import com.ecommerce.kafka.topology.AbstractTopology;
import com.ecommerce.product.service.ProductService;

import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         22 feb 2025
 *
 */
@Component
@RequiredArgsConstructor
//@Log4j2
public class ProductTopology extends AbstractTopology<ProductDTO> {

	private final ProductService service;

	@Override
	protected ProductDTO onCreateEntity(final ProductDTO dto) {
		return service.createECommerceEntity(dto);
	}

	@Override
	protected Class<ProductDTO> provideDTOClass() {
		return ProductDTO.class;
	}

}
