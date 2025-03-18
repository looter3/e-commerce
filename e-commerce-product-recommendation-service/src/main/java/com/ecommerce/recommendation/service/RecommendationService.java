package com.ecommerce.recommendation.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ecommerce.common.dao.ECommerceDAO;
import com.ecommerce.common.dto.RecommendationDTO;
import com.ecommerce.common.service.AbstractECommerceCRUDService;
import com.ecommerce.recommendation.model.Recommendation;

import lombok.extern.log4j.Log4j2;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
		AbstractECommerceCRUDService<RecommendationDTO, Recommendation, RecommendationMapper, ECommerceDAO<Recommendation>> {

	private final RecommendationMapper mapper;

	public RecommendationService(final ECommerceDAO<Recommendation> dao, final RecommendationMapper mapper) {
		super(dao);
		this.mapper = mapper;
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

	public Mono<Void> deleteRecommendationsByProductId(final int productId) {
//		return repository.deleteAll(repository.findByProductId(productId));
		return null;
	}

	@Override
	protected Function<Integer, Mono<Recommendation>> provideFindByIdFunction() {
//		return this.repository::findByRecommendationId;
		return null;
	}

	@Override
	protected RecommendationMapper provideObjectMapper() {
		return this.mapper;
	}

}
