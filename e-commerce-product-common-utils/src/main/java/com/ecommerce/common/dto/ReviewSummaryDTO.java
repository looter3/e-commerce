package com.ecommerce.common.dto;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
//@Data
//@RequiredArgsConstructor
public record ReviewSummaryDTO(int reviewId,
		String author,
		String subject,
		String content) {

}
