package com.ecommerce.recommendation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.api.RecommendationAPI;
import com.ecommerce.common.dto.RecommendationDTO;
import com.ecommerce.recommendation.service.RecommendationService;

import lombok.RequiredArgsConstructor;

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
	public List<RecommendationDTO> getRecommendations(final int productId) {
		return this.service.getAllRecommendationsByProductId(productId);
	}

	@Override
	public RecommendationDTO createRecommendation(final RecommendationDTO body) {
		return service.createECommerceEntity(body);
	}

	@Override
	public void deleteRecommendations(final int productId) {
		this.service.deleteECommerceEntity(productId);
	}

}
