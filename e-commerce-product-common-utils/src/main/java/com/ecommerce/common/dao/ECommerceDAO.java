package com.ecommerce.common.dao;

import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.ecommerce.common.entity.ECommerceEntity;

import lombok.RequiredArgsConstructor;

/**
 * @author Lorenzo Leccese
 *
 *         9 mar 2025
 *
 */
@Component
@RequiredArgsConstructor
public class ECommerceDAO<E extends ECommerceEntity> {

	private final MongoTemplate mongoTemplate;

	public E save(final E entity) {
		try {
			// Check for existing product before saving (if necessary)
			@SuppressWarnings("unchecked")
			final Optional<E> existingProduct = (Optional<E>) Optional.ofNullable(
					mongoTemplate.findById(entity.provideEntityId(), entity.getClass()));

			if (existingProduct.isPresent()) {
				throw new DuplicateKeyException("Product with ID " + entity.provideEntityId() + " already exists.");
			}

			// Save product to MongoDB
			return mongoTemplate.save(entity);
		} catch (final DuplicateKeyException e) {
			throw new DuplicateKeyException("Error saving product: " + e.getMessage());
		}
	}

	// You can also add other methods like findById, delete, etc.
	public Optional<E> findById(final String id, final Class<E> clazz) {
		return Optional.ofNullable(mongoTemplate.findById(id, clazz));
	}

}
