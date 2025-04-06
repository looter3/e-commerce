package com.ecommerce.common.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.kafka.dto.ProductAggregateDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
public interface ProductCompositeAPI {

	/**
	 * Sample usage, see below.
	 *
	 * curl -X POST $HOST:$PORT/product-composite \
	 * -H "Content-Type: application/json" --data \
	 * '{"productId":123,"name":"product 123","weight":123}'
	 *
	 * @param body A JSON representation of the new composite product
	 */
	@Operation(summary = "${api.product-composite.create-composite-product.description}", description = "${api.product-composite.create-composite-product.notes}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
	})
	@PostMapping(value = "/product-composite", consumes = "application/json")
	ResponseEntity<ProductAggregateDTO> createProduct(@RequestBody ProductAggregateDTO body);

	/**
	 * Sample usage: "curl $HOST:$PORT/product-composite/1".
	 *
	 * @param productId Id of the product
	 * @return the composite product info, if found, else null
	 */
	@Operation(summary = "${api.product-composite.get-composite-product.description}", description = "${api.product-composite.get-composite-product.notes}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "${api.responseCodes.ok.description}"),
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "404", description = "${api.responseCodes.notFound.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
	})
	@GetMapping(value = "/product-composite/{productId}", produces = "application/json")
	Mono<ProductAggregateDTO> getProduct(@PathVariable int productId);

	/**
	 * Sample usage: "curl -X DELETE $HOST:$PORT/product-composite/1".
	 *
	 * @param productId Id of the product
	 */
	@Operation(summary = "${api.product-composite.delete-composite-product.description}", description = "${api.product-composite.delete-composite-product.notes}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "${api.responseCodes.badRequest.description}"),
			@ApiResponse(responseCode = "422", description = "${api.responseCodes.unprocessableEntity.description}")
	})
	@DeleteMapping(value = "/product-composite/{productId}")
	ResponseEntity<Void> deleteProduct(@PathVariable int productId);

}
