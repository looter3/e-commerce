package com.ecommerce.product.service;

import org.springframework.stereotype.Service;

import com.ecommerce.common.mongodb.dao.ECommerceDAO;
import com.ecommerce.common.service.ProductAwareService;
import com.ecommerce.kafka.dto.ProductDTO;
import com.ecommerce.product.model.Product;

/**
 * @author Lorenzo Leccese
 *
 *         6 feb 2025
 *
 */
@Service
public class ProductService
		extends ProductAwareService<ProductDTO, Product, ProductMapper, ECommerceDAO<Product>> {

	private final ProductMapper mapper;

	public ProductService(final ECommerceDAO<Product> dao, final ProductMapper mapper) {
		super(dao);
		this.mapper = mapper;
	}

	@Override
	public void deleteByProductId(final int productId) {
		this.dao.deleteByIntegerValue(productId, "productId", Product.class);
	}

	@Override
	protected ProductMapper provideObjectMapper() {
		return this.mapper;
	}

}
