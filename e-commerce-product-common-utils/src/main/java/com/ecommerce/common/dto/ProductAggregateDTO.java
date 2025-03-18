package com.ecommerce.common.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductAggregateDTO extends ECommerceDTO {

	private int productId;
	private String name;
	private int weight;
	private List<RecommendationSummaryDTO> recommendations;
	private List<ReviewSummaryDTO> reviews;

	@Override
	public int getObjectId() {
		return this.productId;
	}

	/**
	 * Transform ProductAggregateDTO to ProductDTO
	 *
	 */
	public static ProductDTO toProductDTO(final ProductAggregateDTO aggregate) {
		return new ProductDTO(aggregate.getProductId(), aggregate.getName(), aggregate.getWeight());
	}

	/**
	 * Transform ProductAggregateDTO to a Stream of ReviewDTOs
	 *
	 */
	public static Stream<ReviewDTO> toReviewDTO(final ProductAggregateDTO aggregate) {
		return Optional.ofNullable(aggregate.getReviews())
			.map(reviews -> reviews.stream()
				.map(r -> new ReviewDTO(aggregate.getProductId(), r.getReviewId(), r.getAuthor(), r.getSubject(), r.getContent())))
			.orElse(Stream.empty()); // Return empty list if reviews is null
	}

	/**
	 * Transform ProductAggregateDTO to a Stream of RecommendationDTOs
	 *
	 */
	public static Stream<RecommendationDTO> toRecommendationDTO(final ProductAggregateDTO aggregate) {
		return Optional.ofNullable(aggregate.getRecommendations())
			.map(recommendations -> recommendations.stream()
				.map(r -> new RecommendationDTO(aggregate.getProductId(), r.getRecommendationId(), r.getAuthor(), r.getRate(), r.getContent())))
			.orElse(Stream.empty()); // Return empty Stream if recommendations is null
	}
}
