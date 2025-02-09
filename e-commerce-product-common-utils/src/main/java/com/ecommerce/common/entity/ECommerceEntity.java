package com.ecommerce.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
//@Data
public abstract class ECommerceEntity {

	@Id
	private String id;

	@Version
	private Integer version;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(final Integer version) {
		this.version = version;
	}

}
