package com.ecommerce.product_composite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.api.ProductCompositeAPI;
import com.ecommerce.common.dto.ProductAggregateDTO;
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

	private final ProductCompositeService integration;

	@Override
	public ResponseEntity<ProductAggregateDTO> createProduct(final ProductAggregateDTO body) {
		return new ResponseEntity<>(integration.createCompositeProduct(body), HttpStatus.OK);
	}

	@Override
	public Mono<Void> deleteProduct(final int productId) {
		return null;
	}

	@Override
	public Mono<ProductAggregateDTO> getProduct(@PathVariable final int productId) {
		return null;
	}

}
