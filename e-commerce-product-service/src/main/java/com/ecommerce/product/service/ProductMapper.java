package com.ecommerce.product.service;

import org.mapstruct.Mapper;

import com.ecommerce.kafka.dto.ProductDTO;
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

}
