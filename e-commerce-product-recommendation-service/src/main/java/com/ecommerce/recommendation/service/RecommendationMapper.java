package com.ecommerce.recommendation.service;

import org.mapstruct.Mapper;

import com.ecommerce.kafka.dto.RecommendationDTO;
import com.ecommerce.common.mapper.ECommerceEntityMapper;
import com.ecommerce.recommendation.model.Recommendation;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Mapper(componentModel = "spring")
public interface RecommendationMapper extends ECommerceEntityMapper<RecommendationDTO, Recommendation> {

	/*-
	@Mappings({
	//			 @Mapping(target = "serviceAddress", ignore = true)
	})
	RecommendationDTO entityToDTO(Recommendation entity);

	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "version", ignore = true)
	})
	Recommendation dtoToEntity(RecommendationDTO dto);
	*/

}
