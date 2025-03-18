package com.ecommerce.recommendation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.api.RecommendationAPI;
import com.ecommerce.common.dto.RecommendationDTO;
import com.ecommerce.recommendation.service.RecommendationService;

import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         1 feb 2025
 *
 */
@RestController
@RequiredArgsConstructor
public class RecommendationController implements RecommendationAPI {

	private final RecommendationService service;

	@Override
	public Flux<RecommendationDTO> getRecommendationsByProductId(final int productId) {
		return this.service.getAllRecommendationsByProductId(productId);
	}

	@Override
	public Mono<RecommendationDTO> createRecommendation(final RecommendationDTO body) {
//		return service.createECommerceEntity(body);
		return null;
	}

	@Override
	public Mono<Void> deleteRecommendationsByProductId(final int productId) {
		return this.service.deleteRecommendationsByProductId(productId);
	}

}
