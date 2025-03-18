package com.ecommerce.product_composite.kafka;

import org.springframework.stereotype.Component;

import com.ecommerce.common.dto.ProductAggregateDTO;
import com.ecommerce.kafka.topology.AbstractTopology;
import com.ecommerce.product_composite.service.ProductCompositeService;

import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         8 mar 2025
 *
 */
@Component
@RequiredArgsConstructor
//@Log4j2
public class ProductCompositeTopology extends AbstractTopology<ProductAggregateDTO> {

	private final ProductCompositeService service;

	@Override
	protected ProductAggregateDTO onCreateEntity(final ProductAggregateDTO dto) {
		return service.createCompositeProduct(dto);
	}

	@Override
	protected Class<ProductAggregateDTO> provideDTOClass() {
		return ProductAggregateDTO.class;
	}

}
