package com.ecommerce.recommendation.kafka;

import org.springframework.stereotype.Component;

import com.ecommerce.common.dto.RecommendationDTO;
import com.ecommerce.kafka.topology.AbstractTopology;
import com.ecommerce.recommendation.service.RecommendationService;

import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         22 feb 2025
 *
 */
@Component
@RequiredArgsConstructor
public class RecommendationTopology extends AbstractTopology<RecommendationDTO> {

	private final RecommendationService service;

	@Override
	protected RecommendationDTO onCreateEntity(final RecommendationDTO dto) {
		return service.createECommerceEntity(dto);
	}

	@Override
	protected Class<RecommendationDTO> provideDTOClass() {
		return RecommendationDTO.class;
	}

	/*-
	@Bean
	KStream<String, String> createReview(final StreamsBuilder kStreamBuilder) {
	
		final KStream<String, RecommendationDTO> stream = kStreamBuilder.stream("create-recommendation-in",
				Consumed.with(Serdes.String(), new JsonSerde<>(RecommendationDTO.class)));
	
		stream.mapValues(service::createECommerceEntity).to("create-recommendation-out");
	
		// Return "COMPLETED" after all messages are sent
		return stream.mapValues(value -> "COMPLETED");
	
	}
	*/

}
