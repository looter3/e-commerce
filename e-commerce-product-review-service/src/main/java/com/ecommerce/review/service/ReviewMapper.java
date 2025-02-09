package com.ecommerce.review.service;

import org.mapstruct.Mapper;

import com.ecommerce.common.dto.ReviewDTO;
import com.ecommerce.common.mapper.ECommerceEntityMapper;
import com.ecommerce.review.model.Review;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Mapper(componentModel = "spring")
public interface ReviewMapper extends ECommerceEntityMapper<ReviewDTO, Review> {

	/*-
	@Mappings({
			// @Mapping(target = "serviceAddress", ignore = true)
	})
	ReviewDTO entityToDTO(Review entity);
	
	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "version", ignore = true)
	})
	Review dtoToEntity(ReviewDTO dto);
	*/

}
