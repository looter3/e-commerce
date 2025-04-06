package com.ecommerce.common.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.ecommerce.common.mongodb.entity.ECommerceEntity;
import com.ecommerce.kafka.dto.ECommerceDTO;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
//@Mapper
public interface ECommerceEntityMapper<DTO extends ECommerceDTO, E extends ECommerceEntity> {

//	@SuppressWarnings("rawtypes")
//	ECommerceEntityMapper INSTANCE = Mappers.getMapper(ECommerceEntityMapper.class);

	@Mappings({
			// @Mapping(target = "serviceAddress", ignore = true)
	})
	DTO entityToDTO(E entity);

	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "version", ignore = true)
	})
	E dtoToEntity(DTO dto);

}
