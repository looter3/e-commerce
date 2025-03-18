package com.ecommerce.review.kafka;

import org.springframework.stereotype.Component;

import com.ecommerce.common.dto.ReviewDTO;
import com.ecommerce.kafka.topology.AbstractTopology;
import com.ecommerce.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         22 feb 2025
 *
 */
@Component
@RequiredArgsConstructor
public class ReviewTopology extends AbstractTopology<ReviewDTO> {

	private final ReviewService service;

	@Override
	protected ReviewDTO onCreateEntity(final ReviewDTO dto) {
		return service.createECommerceEntity(dto);
	}

	@Override
	protected Class<ReviewDTO> provideDTOClass() {
		return ReviewDTO.class;
	}

}
