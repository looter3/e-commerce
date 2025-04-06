package com.ecommerce.recommendation.kafka;

import org.springframework.stereotype.Component;

import com.ecommerce.kafka.dto.RecommendationDTO;
import com.ecommerce.kafka.config.KafkaTopicConfiguration;
import com.ecommerce.kafka.topology.AbstractTopology;
import com.ecommerce.recommendation.service.RecommendationService;

/**
 * @author Lorenzo Leccese
 *
 *         22 feb 2025
 *
 */
@Component
//@RequiredArgsConstructor
public class RecommendationTopology extends AbstractTopology<RecommendationDTO, RecommendationService> {

	public RecommendationTopology(final RecommendationService service, final KafkaTopicConfiguration kafkaTopicConfiguration) {
		super(service, kafkaTopicConfiguration);
	}

	@Override
	protected Class<RecommendationDTO> provideDTOClass() {
		return RecommendationDTO.class;
	}

	/*-
	private final RecommendationService service;

	@Override
	protected RecommendationDTO onCreateEntity(final RecommendationDTO dto) {
		return service.createECommerceEntity(dto);
	}

	@Override
	protected RecommendationDTO onDeleteEntity(final RecommendationDTO dto) {
		return service.deleteECommerceEntity(dto);
	}

	@Override
	protected RecommendationDTO onReadEntity(final RecommendationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	*/
}
