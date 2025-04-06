package com.ecommerce.review.service;

import org.springframework.stereotype.Service;

import com.ecommerce.common.mongodb.dao.ECommerceDAO;
import com.ecommerce.common.service.ProductAwareService;
import com.ecommerce.kafka.dto.ReviewDTO;
import com.ecommerce.review.model.Review;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Service
public class ReviewService extends ProductAwareService<ReviewDTO, Review, ReviewMapper, ECommerceDAO<Review>> {

	private final ReviewMapper mapper;

	public ReviewService(final ECommerceDAO<Review> dao, final ReviewMapper mapper) {
		super(dao);
		this.mapper = mapper;
	}

	@Override
	public void deleteByProductId(final int productId) {
		this.dao.deleteByIntegerValue(productId, "productId", Review.class);
	}

	@Override
	protected ReviewMapper provideObjectMapper() {
		return this.mapper;
	}

}
