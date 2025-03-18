package com.ecommerce.review.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ecommerce.common.dao.ECommerceDAO;
import com.ecommerce.common.dto.ReviewDTO;
import com.ecommerce.common.service.AbstractECommerceCRUDService;
import com.ecommerce.review.model.Review;

import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Service
public class ReviewService extends AbstractECommerceCRUDService<ReviewDTO, Review, ReviewMapper, ECommerceDAO<Review>> {

	private final ReviewMapper mapper;

	public ReviewService(final ECommerceDAO<Review> dao, final ReviewMapper mapper) {
		super(dao);
		this.mapper = mapper;
	}

	public Mono<Void> deleteReviewsByProductId(final int productId) {
//		return repository.deleteAll(repository.findByProductId(productId));
		return null;
	}

	@Override
	protected Function<Integer, Mono<Review>> provideFindByIdFunction() {
//		return this.repository::findByReviewId;
		return null;
	}

	/*-
	@Override
	protected Class<Review> provideEntityClass() {
		return Review.class;
	}
	
	@Override
	protected Class<ReviewDTO> provideDTOClass() {
		return ReviewDTO.class;
	}
	*/

	@Override
	protected ReviewMapper provideObjectMapper() {
		return this.mapper;
	}

}
