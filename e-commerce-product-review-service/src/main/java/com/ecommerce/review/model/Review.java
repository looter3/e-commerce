package com.ecommerce.review.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ecommerce.common.entity.ECommerceEntity;

/**
 * @author Lorenzo Leccese
 *
 *         4 feb 2025
 *
 */
//@Data
@Document
//@EqualsAndHashCode(callSuper = false)
public class Review extends ECommerceEntity {

//	@Id
//	private String id;
//
//	@Version
//	private Integer version;

	@Indexed(unique = true)
	private int productId;

	@Indexed(unique = true)
	private int reviewId;

	private String author;
	private String content;
	private String subject;

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

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@Override
	public int provideEntityId() {
		return this.reviewId;
	}

}
