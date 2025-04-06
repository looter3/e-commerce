package com.ecommerce.kafka.dto;

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
public class ReviewSummaryDTO extends ECommerceDTO {

	private int reviewId;
	private String author;
	private String subject;
	private String content;

	@Override
	public int getObjectId() {
		return this.reviewId;
	}

}
