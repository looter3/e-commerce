package com.ecommerce.product_composite.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.kafka.dto.ProductAggregateDTO;
import com.ecommerce.kafka.dto.ProductDTO;
import com.ecommerce.kafka.dto.RecommendationDTO;
import com.ecommerce.kafka.dto.RecommendationSummaryDTO;
import com.ecommerce.kafka.dto.ReviewDTO;
import com.ecommerce.kafka.dto.ReviewSummaryDTO;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
public class ProductCompositeHelper {

	public static ProductAggregateDTO createProductAggregateDTO(
			final ProductDTO product,
			final List<RecommendationDTO> RecommendationDTOs,
			final List<ReviewDTO> reviews
	/* final String serviceAddress */) {

		// 1. Setup product info
		final int productId = product.getProductId();
		final String name = product.getName();
		final int weight = product.getWeight();

		// 2. Copy summary RecommendationDTO info, if available
		final List<RecommendationSummaryDTO> RecommendationDTOSummaries = (RecommendationDTOs == null) ? null
				: RecommendationDTOs.stream()
					.map(r -> new RecommendationSummaryDTO(r.getRecommendationId(), r.getAuthor(), r.getRating(), r.getContent()))
					.collect(Collectors.toList());

		// 3. Copy summary review info, if available
		final List<ReviewSummaryDTO> reviewSummaries = (reviews == null) ? null
				: reviews.stream()
					.map(r -> new ReviewSummaryDTO(r.getReviewId(), r.getAuthor(), r.getSubject(), r.getContent()))
					.collect(Collectors.toList());

		// 4. Create info regarding the involved microservices addresses
		// final String productAddress = product.getServiceAddress();
		// final String reviewAddress = (reviews != null && reviews.size() > 0) ? reviews.get(0).getServiceAddress() : "";
		// final String RecommendationDTOAddress = (RecommendationDTOs != null && RecommendationDTOs.size() > 0) ?
		// RecommendationDTOs.get(0).getServiceAddress() : "";
		// ServiceAddresses serviceAddresses = new ServiceAddresses(serviceAddress, productAddress, reviewAddress, RecommendationDTOAddress);

		return new ProductAggregateDTO(productId, name, weight, RecommendationDTOSummaries, reviewSummaries);
	}

}
