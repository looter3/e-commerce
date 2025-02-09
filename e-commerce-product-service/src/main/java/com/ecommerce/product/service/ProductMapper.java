package com.ecommerce.product.service;

import org.mapstruct.Mapper;

import com.ecommerce.common.dto.ProductDTO;
import com.ecommerce.common.mapper.ECommerceEntityMapper;
import com.ecommerce.product.model.Product;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends ECommerceEntityMapper<ProductDTO, Product> {

	/*-
	@Override
	@Mappings({
	// @Mapping(target = "serviceAddress", ignore = true)
	})
	ProductDTO entityToDTO(Product entity);
	
	@Override
	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "version", ignore = true)
	})
	Product dtoToEntity(ProductDTO dto);
	*/
}
