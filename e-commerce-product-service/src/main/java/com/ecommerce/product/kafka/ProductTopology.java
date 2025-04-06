package com.ecommerce.product.kafka;

import org.springframework.stereotype.Component;

import com.ecommerce.kafka.dto.ProductDTO;
import com.ecommerce.kafka.config.KafkaTopicConfiguration;
import com.ecommerce.kafka.topology.AbstractTopology;
import com.ecommerce.product.service.ProductService;

/**
 * @author Lorenzo Leccese
 *
 *         22 feb 2025
 *
 */
@Component
//@RequiredArgsConstructor
//@Log4j2
public class ProductTopology extends AbstractTopology<ProductDTO, ProductService> {

	public ProductTopology(final ProductService service, final KafkaTopicConfiguration kafkaTopicConfiguration) {
		super(service, kafkaTopicConfiguration);
	}

	@Override
	protected Class<ProductDTO> provideDTOClass() {
		return ProductDTO.class;
	}

//	private final ProductService service;
	/*-
		@Override
		protected ProductDTO onCreateEntity(final ProductDTO dto) {
			return service.createECommerceEntity(dto);
		}
	
		@Override
		protected ProductDTO onDeleteEntity(final ProductDTO dto) {
			return service.deleteECommerceEntity(dto);
		}
	
		@Override
		protected ProductDTO onReadEntity(final ProductDTO dto) {
			return null;
		}
		*/
}
