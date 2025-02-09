package com.ecommerce.common.dto;

/**
 * @author Lorenzo Leccese
 *
 *         1 feb 2025
 *
 */
public class RecommendationDTO extends ECommerceDTO {

	// private final String serviceAddress;

	private int productId;
	private int recommendationId;
	private String author;
	private int rating;
	private String content;

	@Override
	public int getObjectId() {
		return this.productId;
	}

	public RecommendationDTO(final int productId, final int recommendationId, final String author, final int rating, final String content) {
		this.productId = productId;
		this.recommendationId = recommendationId;
		this.author = author;
		this.rating = rating;
		this.content = content;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(final int productId) {
		this.productId = productId;
	}

	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(final int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(final int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

}
