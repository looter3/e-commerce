package com.ecommerce.common.service;

import java.util.Optional;
import java.util.function.Function;

import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import com.ecommerce.common.dto.ECommerceDTO;
import com.ecommerce.common.entity.ECommerceEntity;
import com.ecommerce.common.exception.InvalidInputException;
import com.ecommerce.common.exception.NotFoundException;
import com.ecommerce.common.mapper.ECommerceEntityMapper;
import com.ecommerce.common.repository.ECommerceRepository;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
//@Log4j2
//@RequiredArgsConstructor
public abstract class AbstractECommerceCRUDService<
		DTO extends ECommerceDTO,
		E extends ECommerceEntity,
		M extends ECommerceEntityMapper<DTO, E>,
		R extends ECommerceRepository<E>>
		implements ECommerceCRUDService<DTO> {

	protected final R repository;
//	protected final ModelMapper modelMapper;
//	protected final M mapper;
	private final Function<Integer, Optional<E>> findById;
//	private final Class<DTO> dtoClass;
//	private final Class<E> entityClass;

	public AbstractECommerceCRUDService(final R repository) {
		this.repository = repository;
		this.findById = provideFindByIdFunction();
//		this.mapper = this.provideObjectMapper();
//		this.dtoClass = this.provideDTOClass();
//		this.entityClass = this.provideEntityClass();
	}

	@Override
	public DTO createECommerceEntity(final DTO dto) {
		try {
//			final E entity = this.modelMapper.map(dto, entityClass);
			final E entity = this.provideObjectMapper().dtoToEntity(dto);
			repository.save(entity);
//			final DTO dtoToReturn = this.modelMapper.map(entity, dtoClass);
			final DTO dtoToReturn = this.provideObjectMapper().entityToDTO(entity);

			return dtoToReturn;
		} catch (final DuplicateKeyException e) {
			final String entityName = dto.getClass().getSimpleName().replace("DTO", "");
			throw new InvalidInputException("Duplicate key, " + entityName + " Id: " + dto.getObjectId());
		}
	}

	@Override
	public void deleteECommerceEntity(final int id) {
		// log.debug("deleteECommerceEntity: tries to delete an entity with id: {}", id);
		this.findById.apply(id).ifPresent(repository::delete);
	}

	@Override
	public DTO getECommerceEntity(final int id) {

		if (id < 1) {
			throw new InvalidInputException("Invalid id: " + id);
		}

		final E entity = this.findById.apply(id)
			.orElseThrow(() -> new NotFoundException("No entity found for id: " + id));

//		final DTO response = this.modelMapper.map(entity, dtoClass);
		final DTO response = this.provideObjectMapper().entityToDTO(entity);
		// response.setServiceAddress(serviceUtil.getServiceAddress());

		// log.debug("getProduct: found productId: {}", response.getObjectId());

		return response;
	}

	protected abstract Function<Integer, Optional<E>> provideFindByIdFunction();

	protected abstract M provideObjectMapper();

	/*-
	protected abstract Class<E> provideEntityClass();
	
	protected abstract Class<DTO> provideDTOClass();
	*/

}
