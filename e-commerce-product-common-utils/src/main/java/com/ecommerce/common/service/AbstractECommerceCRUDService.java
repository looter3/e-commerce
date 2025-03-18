package com.ecommerce.common.service;

import java.util.function.Function;

import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import com.ecommerce.common.dao.ECommerceDAO;
import com.ecommerce.common.dto.ECommerceDTO;
import com.ecommerce.common.entity.ECommerceEntity;
import com.ecommerce.common.exception.InvalidInputException;
import com.ecommerce.common.mapper.ECommerceEntityMapper;

import lombok.extern.log4j.Log4j2;

import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
@Log4j2
public abstract class AbstractECommerceCRUDService<
		DTO extends ECommerceDTO,
		E extends ECommerceEntity,
		M extends ECommerceEntityMapper<DTO, E>,
//		R extends ECommerceRepository<E>,
		DAO extends ECommerceDAO<E>>
		implements ECommerceCRUDService<DTO> {

//	protected final R repository;
	private final Function<Integer, Mono<E>> findById;
	private final DAO dao;

	public AbstractECommerceCRUDService(final DAO dao) {
//		this.repository = repository;
		this.findById = provideFindByIdFunction();
		this.dao = dao;
	}

	@Override
	public DTO createECommerceEntity(final DTO dto) {
		try {
			final E entity = this.provideObjectMapper().dtoToEntity(dto);
			final DTO dtoToReturn = this.provideObjectMapper().entityToDTO(dao.save(entity));

			return dtoToReturn;
		} catch (final DuplicateKeyException e) {
			final String entityName = dto.getClass().getSimpleName().replace("DTO", "");
			throw new InvalidInputException("Duplicate key, " + entityName + " Id: " + dto.getObjectId());
		}
	}

	@Override
	public Mono<Void> deleteECommerceEntity(final int id) {
		/*-
		log.debug("deleteECommerceEntity: tries to delete an entity with id: {}", id);
		return this.findById.apply(id)
			.map(repository::delete)
			.flatMap(e -> e);
		*/
		return null;
	}

	@Override
	public Mono<DTO> getECommerceEntity(final int id) {

		/*-
		if (id < 1) {
			throw new InvalidInputException("Invalid id: " + id);
		}
		
		return this.findById.apply(id)
			.switchIfEmpty(Mono.error(new NotFoundException("No entity found for id: " + id)))
			.map(e -> this.provideObjectMapper().entityToDTO(e));
		*/
		return null;
	}

	protected abstract Function<Integer, Mono<E>> provideFindByIdFunction();

	protected abstract M provideObjectMapper();

}
