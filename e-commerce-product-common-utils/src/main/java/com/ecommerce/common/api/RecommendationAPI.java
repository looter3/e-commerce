package com.ecommerce.common.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.common.dto.RecommendationDTO;

/**
 * @author Lorenzo Leccese
 *
 *         2 feb 2025
 *
 */
public interface RecommendationAPI {

	@GetMapping(value = "/recommendation/{productId}", produces = "application/json")
	List<RecommendationDTO> getRecommendations(@PathVariable final int productId);

	@PostMapping(value = { "/recommendation", "/recommendation/" }, consumes = "application/json")
	RecommendationDTO createRecommendation(@RequestBody RecommendationDTO body);

	@DeleteMapping(value = "/recommendation/{productId}")
	void deleteRecommendationsByProductId(@PathVariable int productId);

}
