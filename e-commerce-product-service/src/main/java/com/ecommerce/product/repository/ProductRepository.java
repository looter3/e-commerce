package com.ecommerce.product.repository;

import java.util.Optional;

import com.ecommerce.common.repository.ECommerceRepository;
import com.ecommerce.product.model.Product;

/**
 * @author Lorenzo Leccese
 *
 *         5 feb 2025
 *
 */
public interface ProductRepository extends ECommerceRepository<Product> {

	Optional<Product> findByProductId(int productId);

}
