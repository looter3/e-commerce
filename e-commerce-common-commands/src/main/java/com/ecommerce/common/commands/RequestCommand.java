package com.ecommerce.common.commands;

import com.ecommerce.common.service.ECommerceCRUDService;
import com.ecommerce.kafka.dto.ECommerceDTO;
import com.ecommerce.kafka.request.Command;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author lex_looter
 *
 *         6 apr 2025
 *
 */
public abstract class RequestCommand implements Command {

	protected static final ObjectMapper objectMapper = new ObjectMapper();

	public abstract void setService(ECommerceCRUDService<ECommerceDTO> service);

//	public abstract void execute();

}
