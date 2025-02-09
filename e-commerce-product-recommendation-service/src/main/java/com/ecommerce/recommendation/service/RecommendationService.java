package com.ecommerce.recommendation.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.common.dto.RecommendationDTO;
import com.ecommerce.common.service.AbstractECommerceCRUDService;
import com.ecommerce.recommendation.model.Recommendation;
import com.ecommerce.recommendation.repository.RecommendationRepository;

import lombok.extern.log4j.Log4j2;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Service
@Log4j2
public class RecommendationService extends AbstractECommerceCRUDService<RecommendationDTO, Recommendation, RecommendationMapper, RecommendationRepository> {

	private final RecommendationMapper mapper;

	public RecommendationService(final RecommendationRepository repository, final RecommendationMapper mapper) {
		super(repository);
		this.mapper = mapper;
	}

	public List<RecommendationDTO> getAllRecommendationsByProductId(final int productId) {

		return this.repository.findByProductId(productId)
			.stream()
			.parallel()
			.map(entity -> this.mapper.entityToDTO(entity))
			.collect(Collectors.toList());

	}

	public void deleteRecommendationsByProductId(final int productId) {
		repository.deleteAll(repository.findByProductId(productId));
	}

	@Override
	protected Function<Integer, Optional<Recommendation>> provideFindByIdFunction() {
		return this.repository::findByRecommendationId;
	}

	@Override
	protected RecommendationMapper provideObjectMapper() {
		return this.mapper;
	}

	/*-
	@Override
	protected Class<Recommendation> provideEntityClass() {
		return Recommendation.class;
	}

	@Override
	protected Class<RecommendationDTO> provideDTOClass() {
		return RecommendationDTO.class;
	}
	*/

}
