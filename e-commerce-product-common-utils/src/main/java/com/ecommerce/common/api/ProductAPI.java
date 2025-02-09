package com.ecommerce.common.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.common.dto.ProductDTO;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
public interface ProductAPI {

	@GetMapping(value = "/product/{productId}", produces = "application/json")
	ProductDTO getProduct(@PathVariable int productId);

	@PostMapping(value = { "/product", "/product/" }, consumes = "application/json")
	void createProduct(@RequestBody ProductDTO body);

	@DeleteMapping(value = "/product/{productId}")
	void deleteProduct(@PathVariable int productId);

}
