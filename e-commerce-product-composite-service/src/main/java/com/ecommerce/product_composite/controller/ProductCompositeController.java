package com.ecommerce.product_composite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.api.ProductCompositeAPI;
import com.ecommerce.kafka.dto.ProductAggregateDTO;
import com.ecommerce.kafka.request.ECommerceKafkaRequest;
import com.ecommerce.kafka.request.SimpleCRUDRequest;
import com.ecommerce.product_composite.service.ProductCompositeService;

import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@RestController
@RequiredArgsConstructor
//@Log4j2
public class ProductCompositeController implements ProductCompositeAPI {

	private final ProductCompositeService service;

	@Override
	public ResponseEntity<ProductAggregateDTO> createProduct(final ProductAggregateDTO body) {

		final ECommerceKafkaRequest request = new ECommerceKafkaRequest(SimpleCRUDRequest.CREATE, body);
		service.getKafkaTemplate().send("product-composite-service-in", request);

		return new ResponseEntity<>(body, HttpStatus.OK);
//		return new ResponseEntity<>(service.createECommerceEntity(body), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deleteProduct(final int productId) {
		final ECommerceKafkaRequest request = new ECommerceKafkaRequest(SimpleCRUDRequest.DELETE, productId);
		service.getKafkaTemplate().send("product-composite-service-in", request);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public Mono<ProductAggregateDTO> getProduct(@PathVariable final int productId) {
		return null;
	}

}
