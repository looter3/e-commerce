package com.ecommerce.common.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.kafka.dto.RecommendationDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
public interface RecommendationAPI {

	@GetMapping(value = "/recommendation/{productId}", produces = "application/json")
	Flux<RecommendationDTO> getRecommendationsByProductId(@PathVariable final int productId);

	@PostMapping(value = { "/recommendation", "/recommendation/" }, consumes = "application/json")
	Mono<RecommendationDTO> createRecommendation(@RequestBody RecommendationDTO body);

	@DeleteMapping(value = "/recommendation/{productId}")
	Mono<Void> deleteRecommendationsByProductId(@PathVariable int productId);

}
