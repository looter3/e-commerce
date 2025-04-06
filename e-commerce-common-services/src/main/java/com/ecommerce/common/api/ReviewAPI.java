package com.ecommerce.common.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.kafka.dto.ReviewDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
public interface ReviewAPI {

	@GetMapping(value = "/review/{productId}", produces = "application/json")
	Flux<ReviewDTO> getReviewsByProductId(@PathVariable final int productId);

	@PostMapping(value = { "/review", "/review/" }, consumes = "application/json")
	Mono<ReviewDTO> createReview(@RequestBody ReviewDTO body);

	@DeleteMapping(value = "/review/{productId}")
	Mono<Void> deleteReviewsByProductId(@PathVariable int productId);

}
