package com.ecommerce.common.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.common.dto.ReviewDTO;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
public interface ReviewAPI {

	@GetMapping(value = "/review/{productId}", produces = "application/json")
	List<ReviewDTO> getReviewsByProductId(@PathVariable final int productId);

	@PostMapping(value = { "/review", "/review/" }, consumes = "application/json")
	ReviewDTO createReview(@RequestBody ReviewDTO body);

	@DeleteMapping(value = "/recommendation/{productId}")
	void deleteReviewsByProductId(@PathVariable int productId);

}
