package com.ecommerce.product_composite.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.api.ProductCompositeAPI;
import com.ecommerce.common.dto.ProductAggregateDTO;
import com.ecommerce.common.dto.ProductDTO;
import com.ecommerce.common.dto.RecommendationDTO;
import com.ecommerce.common.dto.ReviewDTO;
import com.ecommerce.product_composite.helper.ProductCompositeHelper;
import com.ecommerce.product_composite.service.ProductCompositeIntegrationService;

import lombok.RequiredArgsConstructor;

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

	private final ProductCompositeIntegrationService integration;

	@Override
	public void createProduct(final ProductAggregateDTO body) {

		try {

//			log.debug("createCompositeProduct: creates a new composite entity for productId: {}", body.getProductId());

			final ProductDTO product = new ProductDTO(body.getProductId(), body.getName(), body.getWeight());
			integration.createProduct(product);

			if (body.getRecommendations() != null) {
				body.getRecommendations()
					.stream()
					.map(r -> new RecommendationDTO(body.getProductId(), r.recommendationId(), r.author(), r.rate(), r.content()))
					.forEach(integration::createRecommendation);
			}

			if (body.getReviews() != null) {
				body.getReviews()
					.stream()
					.map(r -> new ReviewDTO(body.getProductId(), r.reviewId(), r.author(), r.subject(), r.content()))
					.forEach(integration::createReview);
			}

//			log.debug("createCompositeProduct: composite entities created for productId: {}", body.getProductId());

		} catch (final RuntimeException re) {
//			log.warn("createCompositeProduct failed", re);
			throw re;
		}
	}

	@Override
	public void deleteProduct(final int productId) {
		integration.deleteProduct(productId);
		integration.deleteReviewsByProductId(productId);
		integration.deleteRecommendations(productId);
	}

	@Override
	public ProductAggregateDTO getProduct(@PathVariable final int productId) {

		final ProductDTO product = integration.getProduct(productId);

		final List<RecommendationDTO> recommendations = integration.getRecommendations(productId);

		final List<ReviewDTO> reviews = integration.getReviewsByProductId(productId);

		return ProductCompositeHelper.createProductAggregateDTO(product, recommendations, reviews);

	}

}
