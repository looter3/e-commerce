package com.ecommerce.product_composite.kafka;

import org.springframework.stereotype.Component;

import com.ecommerce.kafka.dto.ProductAggregateDTO;
import com.ecommerce.kafka.config.KafkaTopicConfiguration;
import com.ecommerce.kafka.topology.AbstractTopology;
import com.ecommerce.product_composite.service.ProductCompositeService;

/**
 * @author Lorenzo Leccese
 *
 *         8 mar 2025
 *
 */
@Component
//@RequiredArgsConstructor
//@Log4j2
public class ProductCompositeTopology extends AbstractTopology<ProductAggregateDTO, ProductCompositeService> {

	public ProductCompositeTopology(final ProductCompositeService service, final KafkaTopicConfiguration kafkaTopicConfiguration) {
		super(service, kafkaTopicConfiguration);
	}

	@Override
	protected Class<ProductAggregateDTO> provideDTOClass() {
		return ProductAggregateDTO.class;
	}

//	private final ProductCompositeService service;

	/*-
	public ProductCompositeTopology(final ProductCompositeService service) {
		super(service);
	}
	
	@Override
	protected ProductAggregateDTO onCreateEntity(final ProductAggregateDTO dto) {
	//		return service.createCompositeProduct(dto);
	}

	@Override
	protected ProductAggregateDTO onDeleteEntity(final ProductAggregateDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected ProductAggregateDTO onReadEntity(final ProductAggregateDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
