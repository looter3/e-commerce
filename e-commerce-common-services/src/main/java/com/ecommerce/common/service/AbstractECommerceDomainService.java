package com.ecommerce.common.service;

import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import com.ecommerce.common.mapper.ECommerceEntityMapper;
import com.ecommerce.common.mongodb.dao.ECommerceDAO;
import com.ecommerce.common.mongodb.entity.ECommerceEntity;
import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.kafka.dto.ECommerceDTO;

import lombok.extern.log4j.Log4j2;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
@Log4j2
public abstract class AbstractECommerceDomainService<
		DTO extends ECommerceDTO,
		E extends ECommerceEntity,
		M extends ECommerceEntityMapper<DTO, E>,
		DAO extends ECommerceDAO<E>>
		implements ECommerceCRUDService<DTO> {

	protected final DAO dao;
	protected M objectMapper;

	public AbstractECommerceDomainService(final DAO dao) {
		this.dao = dao;
	}

	@PostConstruct
	private void init() {
		this.objectMapper = this.provideObjectMapper();
	}

	@Override
	public DTO createECommerceEntity(final DTO dto) {
		try {
			final E entity = objectMapper.dtoToEntity(dto);
			final DTO dtoToReturn = objectMapper.entityToDTO(dao.save(entity));

			return dtoToReturn;
		} catch (final DuplicateKeyException e) {
			final String entityName = dto.getClass().getSimpleName().replace("DTO", "");
			throw new InvalidInputException("Duplicate key, " + entityName + " Id: " + dto.getObjectId());
		}
	}

	@Override
	public void deleteECommerceEntity(final int id) {

		log.debug("deleteECommerceEntity: tries to delete an entity with id: {}", id);

//		dao.delete(id);

	}

	@Override
	public Mono<DTO> getECommerceEntity(final int id) {
		return null;
	}

	protected abstract M provideObjectMapper();

}
