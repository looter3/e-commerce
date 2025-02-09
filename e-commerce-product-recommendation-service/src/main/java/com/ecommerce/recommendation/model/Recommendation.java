package com.ecommerce.recommendation.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecommerce.common.entity.ECommerceEntity;

/**
 * @author Lorenzo Leccese
 *
 *         4 feb 2025
 *
 */
//@Data
//@EqualsAndHashCode(callSuper = false)
@Document
@CompoundIndex(name = "prod-rec-id", unique = true, def = "{'productId': 1, 'recommendationId': 1}")
public class Recommendation extends ECommerceEntity {

//	@Id
//	private String id;
//
//	@Version
//	private Integer version;

	private int productId;
	private int recommendationId;
	private int rating;
	private String author;
	private String content;

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

	public int getRating() {
		return rating;
	}

	public void setRating(final int rating) {
		this.rating = rating;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

}
