package com.ecommerce.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.common.repository.ECommerceRepository;
import com.ecommerce.review.model.Review;

/**
 * @author Lorenzo Leccese
 *
 *         5 feb 2025
 *
 */
public interface ReviewRepository extends ECommerceRepository<Review> {

	@Transactional(readOnly = true)
	List<Review> findByProductId(int productId);

	Optional<Review> findByReviewId(int reviewId);

}
