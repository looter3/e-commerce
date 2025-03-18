package com.ecommerce.product_composite.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.common.dto.ECommerceDTO;
import com.ecommerce.common.dto.ProductAggregateDTO;
import com.ecommerce.common.dto.ProductDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@Service
@Data
//@Log4j2
@RequiredArgsConstructor
public class ProductCompositeService {

	private final KafkaTemplate<String, ECommerceDTO> kafkaTemplate;

	public ProductAggregateDTO createCompositeProduct(final ProductAggregateDTO value) {
		final ProductDTO product = ProductAggregateDTO.toProductDTO(value);
		kafkaTemplate.send("create-product-in", product);

		ProductAggregateDTO.toReviewDTO(value).parallel()
			.forEach(review -> kafkaTemplate.send("create-review-in", review));

		ProductAggregateDTO.toRecommendationDTO(value).parallel()
			.forEach(recommendation -> kafkaTemplate.send("create-recommendation-in", recommendation));

		return value;
	}

}
