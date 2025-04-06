package com.ecommerce.product_composite.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.common.service.AbstractECommerceCompositeService;
import com.ecommerce.kafka.dto.ProductAggregateDTO;
import com.ecommerce.kafka.dto.ProductDTO;
import com.ecommerce.kafka.request.ECommerceKafkaRequest;
import com.ecommerce.kafka.request.ProductAwareRequest;
import com.ecommerce.kafka.request.SimpleCRUDRequest;

import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@Service
//@RequiredArgsConstructor
public class ProductCompositeService extends AbstractECommerceCompositeService<ProductAggregateDTO> {

	public ProductCompositeService(final KafkaTemplate<String, ECommerceKafkaRequest> kafkaTemplate) {
		super(kafkaTemplate);
	}

	// TODO centralize topic names in a configuration object/service
	@Override
	public ProductAggregateDTO createECommerceEntity(final ProductAggregateDTO dto) {
		final ProductDTO product = ProductAggregateDTO.toProductDTO(dto);
		final ECommerceKafkaRequest createProductRequest = new ECommerceKafkaRequest(SimpleCRUDRequest.CREATE, product);
		kafkaTemplate.send("product-service-in", createProductRequest);

		ProductAggregateDTO.toReviewDTO(dto).parallel()
			.map(review -> new ECommerceKafkaRequest(SimpleCRUDRequest.CREATE, review))
			.forEach(request -> kafkaTemplate.send("review-service-in", request));

		ProductAggregateDTO.toRecommendationDTO(dto).parallel()
			.map(recommendation -> new ECommerceKafkaRequest(SimpleCRUDRequest.CREATE, recommendation))
			.forEach(request -> kafkaTemplate.send("recommendation-service-in", request));

		return dto;
	}

	@Override
	public void deleteECommerceEntity(final int id) {

		final ECommerceKafkaRequest deleteByProductId = new ECommerceKafkaRequest(ProductAwareRequest.DELETE_BY_PRODUCT_ID, id);

		kafkaTemplate.send("product-service-in", deleteByProductId);
		kafkaTemplate.send("review-service-in", deleteByProductId);
		kafkaTemplate.send("recommendation-service-in", deleteByProductId);
	}

	@Override
	public Mono<ProductAggregateDTO> getECommerceEntity(final int id) {
		return null;
	}

}
