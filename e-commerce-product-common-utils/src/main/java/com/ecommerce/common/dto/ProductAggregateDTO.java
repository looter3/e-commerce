package com.ecommerce.common.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
@Data
@AllArgsConstructor
public class ProductAggregateDTO {
	// private final ServiceAddresses serviceAddresses;
	private int productId;
	private String name;
	private int weight;
	private List<RecommendationSummaryDTO> recommendations;
	private List<ReviewSummaryDTO> reviews;
}
