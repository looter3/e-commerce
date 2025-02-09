package com.ecommerce.product.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.api.ProductAPI;
import com.ecommerce.common.dto.ProductDTO;
import com.ecommerce.product.service.ProductService;

import lombok.RequiredArgsConstructor;

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
	public ProductDTO getProduct(final int productId) {
		return this.productService.getECommerceEntity(productId);
	}

	@Override
	public void createProduct(final ProductDTO body) {
		this.productService.createECommerceEntity(body);
	}

	@Override
	public void deleteProduct(final int productId) {
		this.productService.deleteECommerceEntity(productId);
	}

}
