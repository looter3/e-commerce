package com.ecommerce.recommendation.service;

import org.springframework.stereotype.Service;

import com.ecommerce.common.mongodb.dao.ECommerceDAO;
import com.ecommerce.common.service.ProductAwareService;
import com.ecommerce.kafka.dto.RecommendationDTO;
import com.ecommerce.recommendation.model.Recommendation;

import lombok.extern.log4j.Log4j2;

import reactor.core.publisher.Flux;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Service
@Log4j2
public class RecommendationService
		extends
		ProductAwareService<RecommendationDTO, Recommendation, RecommendationMapper, ECommerceDAO<Recommendation>> {

	private final RecommendationMapper mapper;

	public RecommendationService(final ECommerceDAO<Recommendation> dao, final RecommendationMapper mapper) {
		super(dao);
		this.mapper = mapper;
	}

	@Override
	public void deleteByProductId(final int productId) {
		this.dao.deleteByIntegerValue(productId, "productId", Recommendation.class);
	}

	public Flux<RecommendationDTO> getAllRecommendationsByProductId(final int productId) {

		/*-
		return this.repository.findByProductId(productId)
		//			.stream()
			.parallel()
			.map(entity -> this.mapper.entityToDTO(entity))
			.sequential()
		//			.collectList()
		//			.blockOptional().orElse(Collections.emptyList())
		;
		*/
		return null;

	}

	@Override
	protected RecommendationMapper provideObjectMapper() {
		return this.mapper;
	}

}
