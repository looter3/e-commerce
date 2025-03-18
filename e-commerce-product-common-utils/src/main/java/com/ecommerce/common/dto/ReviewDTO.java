package com.ecommerce.common.dto;

import lombok.NoArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         1 feb 2025
 *
 */
@NoArgsConstructor
public class ReviewDTO extends ECommerceDTO {
	// private final String serviceAddress;

	private int productId;
	private int reviewId;
	private String author;
	private String subject;
	private String content;

	@Override
	public int getObjectId() {
		return this.productId;
	}

	public ReviewDTO(final int productId, final int reviewId, final String author, final String subject, final String content) {
		this.productId = productId;
		this.reviewId = reviewId;
		this.author = author;
		this.subject = subject;
		this.content = content;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(final int productId) {
		this.productId = productId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(final int reviewId) {
		this.reviewId = reviewId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

}
