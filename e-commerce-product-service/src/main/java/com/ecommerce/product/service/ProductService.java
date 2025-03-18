package com.ecommerce.product.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ecommerce.common.dao.ECommerceDAO;
import com.ecommerce.common.dto.ProductDTO;
import com.ecommerce.common.service.AbstractECommerceCRUDService;
import com.ecommerce.product.model.Product;

import reactor.core.publisher.Mono;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Service
public class ProductService
		extends AbstractECommerceCRUDService<ProductDTO, Product, ProductMapper, ECommerceDAO<Product>> {

	private final ProductMapper mapper;

	public ProductService(final ECommerceDAO<Product> dao, final ProductMapper mapper) {
		super(dao);
		this.mapper = mapper;
	}

	@Override
	protected Function<Integer, Mono<Product>> provideFindByIdFunction() {
//		return this.repository::findByProductId;
		return null;
	}

	@Override
	protected ProductMapper provideObjectMapper() {
		return this.mapper;
	}

	/*-
	@Override
	protected Class<Product> provideEntityClass() {
		return Product.class;
	}
	
	@Override
	protected Class<ProductDTO> provideDTOClass() {
		return ProductDTO.class;
	}
	*/

}
