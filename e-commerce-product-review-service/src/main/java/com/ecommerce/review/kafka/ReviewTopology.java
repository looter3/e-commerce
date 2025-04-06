package com.ecommerce.review.kafka;

import org.springframework.stereotype.Component;

import com.ecommerce.kafka.dto.ReviewDTO;
import com.ecommerce.kafka.config.KafkaTopicConfiguration;
import com.ecommerce.kafka.topology.AbstractTopology;
import com.ecommerce.review.service.ReviewService;

/**
 * @author Lorenzo Leccese
 *
 *         22 feb 2025
 *
 */
@Component
//@RequiredArgsConstructor
public class ReviewTopology extends AbstractTopology<ReviewDTO, ReviewService> {

	public ReviewTopology(final ReviewService service, final KafkaTopicConfiguration kafkaTopicConfiguration) {
		super(service, kafkaTopicConfiguration);
	}

	@Override
	protected Class<ReviewDTO> provideDTOClass() {
		return ReviewDTO.class;
	}

	/*-
	private final ReviewService service;
	
	@Override
	protected ReviewDTO onCreateEntity(final ReviewDTO dto) {
	//		return service.createECommerceEntity(dto);
	}
	
	@Override
	protected ReviewDTO onDeleteEntity(final ReviewDTO dto) {
	//		return service.deleteECommerceEntity(dto.getObjectId());
	}
	
	@Override
	protected ReviewDTO onReadEntity(final ReviewDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/
}
