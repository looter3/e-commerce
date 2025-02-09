package com.ecommerce.review.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ecommerce.common.dto.ReviewDTO;
import com.ecommerce.common.service.AbstractECommerceCRUDService;
import com.ecommerce.review.model.Review;
import com.ecommerce.review.repository.ReviewRepository;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Service
public class ReviewService extends AbstractECommerceCRUDService<ReviewDTO, Review, ReviewMapper, ReviewRepository> {

	private final ReviewMapper mapper;

	public ReviewService(final ReviewRepository repository, final ReviewMapper mapper) {
		super(repository);
		this.mapper = mapper;
	}

	public void deleteReviewsByProductId(final int productId) {
		repository.deleteAll(repository.findByProductId(productId));
	}

	@Override
	protected Function<Integer, Optional<Review>> provideFindByIdFunction() {
		return this.repository::findByReviewId;
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
