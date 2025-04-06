package com.ecommerce.common.commands;

import java.util.Optional;

import com.ecommerce.common.service.ECommerceCRUDService;
import com.ecommerce.common.service.ProductAwareService;
import com.ecommerce.kafka.dto.ECommerceDTO;
import com.ecommerce.kafka.dto.ProductAggregateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author lex_looter
 *
 *         6 apr 2025
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ProductAwareServiceCommand extends RequestCommand {

	@JsonIgnore
	protected ProductAwareService service;

	public ProductAwareServiceCommand() {}

	public ProductAwareService getService() {
		return service;
	}

	public void setService(final ProductAwareService service) {
		this.service = service;
	}

//	public abstract void execute();

	public static class CreateProductCompositeCommand extends ProductAwareServiceCommand {

		private final ProductAggregateDTO dto;

		public CreateProductCompositeCommand(final ProductAggregateDTO dto) {
			this.dto = dto;
		}

		@Override
		public void execute() {
			Optional.ofNullable(service).ifPresent(s -> s.createECommerceEntity(dto));
		}

		@Override
		public void setService(final ECommerceCRUDService<ECommerceDTO> service) {
			this.service = (ProductAwareService) service;
		}

	}
}
