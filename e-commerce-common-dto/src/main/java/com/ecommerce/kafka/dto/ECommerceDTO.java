package com.ecommerce.kafka.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ECommerceDTO {

	@JsonIgnore
	public abstract int getObjectId();

}
