package com.ecommerce.review.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.api.ReviewAPI;
import com.ecommerce.kafka.dto.ReviewDTO;
import com.ecommerce.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         1 feb 2025
 *
 */
@RequiredArgsConstructor
@RestController
public class ReviewController implements ReviewAPI {

	private final ReviewService service;

	@Override
	public Flux<ReviewDTO> getReviewsByProductId(final int productId) {

		/*-
		final List<ReviewDTO> list = new ArrayList<>();
		list.add(new ReviewDTO(productId, 1, "Author 1", "Subject 1", "Content 1"));
		list.add(new ReviewDTO(productId, 2, "Author 2", "Subject 2", "Content 2"));
		list.add(new ReviewDTO(productId, 3, "Author 3", "Subject 3", "Content 3"));

		return list;
		*/
		return null;
	}

	@Override
	public Mono<ReviewDTO> createReview(final ReviewDTO body) {
		// return service.createECommerceEntity(body);
		return null;
	}

	@Override
	public Mono<Void> deleteReviewsByProductId(final int productId) {
		service.deleteByProductId(productId);
		return null;
	}

}
