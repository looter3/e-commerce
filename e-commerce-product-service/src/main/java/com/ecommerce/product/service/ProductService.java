package com.ecommerce.product.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.ecommerce.common.dto.ProductDTO;
import com.ecommerce.common.service.AbstractECommerceCRUDService;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Service
public class ProductService
		extends AbstractECommerceCRUDService<ProductDTO, Product, ProductMapper, ProductRepository> {

	private final ProductMapper mapper;

	public ProductService(final ProductRepository repository, final ProductMapper mapper) {
		super(repository);
		this.mapper = mapper;
	}

	@Override
	protected Function<Integer, Optional<Product>> provideFindByIdFunction() {
		return this.repository::findByProductId;
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
