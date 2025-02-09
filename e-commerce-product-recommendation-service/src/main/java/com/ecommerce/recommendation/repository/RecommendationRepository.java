package com.ecommerce.recommendation.repository;

import java.util.List;
import java.util.Optional;

import com.ecommerce.common.repository.ECommerceRepository;
import com.ecommerce.recommendation.model.Recommendation;

/**
 * @author Lorenzo Leccese
 *
 *         5 feb 2025
 *
 */
public interface RecommendationRepository extends ECommerceRepository<Recommendation> {

	List<Recommendation> findByProductId(int productId);

	Optional<Recommendation> findByRecommendationId(int recommendationId);

}
