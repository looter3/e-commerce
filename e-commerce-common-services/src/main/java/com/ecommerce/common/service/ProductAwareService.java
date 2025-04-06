package com.ecommerce.common.service;

import com.ecommerce.common.mapper.ECommerceEntityMapper;
import com.ecommerce.common.mongodb.dao.ECommerceDAO;
import com.ecommerce.common.mongodb.entity.ECommerceEntity;
import com.ecommerce.kafka.dto.ECommerceDTO;

/**
 * @author lex_looter
 *
 *         6 apr 2025
 *
 */
public abstract class ProductAwareService<
		DTO extends ECommerceDTO,
		E extends ECommerceEntity,
		M extends ECommerceEntityMapper<DTO, E>,
		DAO extends ECommerceDAO<E>>
		extends AbstractECommerceDomainService<DTO, E, M, DAO> {

	public ProductAwareService(final DAO dao) {
		super(dao);
	}

	public abstract void deleteByProductId(int productId);

}
