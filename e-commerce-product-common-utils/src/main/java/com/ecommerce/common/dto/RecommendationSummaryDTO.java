package com.ecommerce.common.dto;

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
public class RecommendationSummaryDTO extends ECommerceDTO {

	private int recommendationId;
	private String author;
	private int rate;
	private String content;

	@Override
	public int getObjectId() {
		return this.recommendationId;
	}
}
