package com.ecommerce.product.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.api.ProductAPI;
import com.ecommerce.common.dto.ProductDTO;
import com.ecommerce.product.service.ProductService;

import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         1 feb 2025
 *
 */
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductAPI {

	private final ProductService productService;

	/*-
	public ProductController(final ProductService productService) {
		this.productService = productService;
	}
	*/

	@Override
	public Mono<ProductDTO> getProduct(final int productId) {
		return this.productService.getECommerceEntity(productId);
	}

	@Override
	public ProductDTO createProduct(final ProductDTO body) {
		return this.productService.createECommerceEntity(body);
	}

	@Override
	public Mono<Void> deleteProduct(final int productId) {
		return this.productService.deleteECommerceEntity(productId);
	}

}
