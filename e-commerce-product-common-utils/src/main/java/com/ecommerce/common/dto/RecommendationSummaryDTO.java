package com.ecommerce.common.dto;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
public record RecommendationSummaryDTO(int recommendationId,
		String author,
		int rate,
		String content) {
}
