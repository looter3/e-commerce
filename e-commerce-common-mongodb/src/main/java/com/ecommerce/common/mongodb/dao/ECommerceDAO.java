package com.ecommerce.common.mongodb.dao;

import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ecommerce.common.mongodb.entity.ECommerceEntity;

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
			// Check for existing entity before saving (if necessary)
			@SuppressWarnings("unchecked")
			final Optional<E> existingEntity = (Optional<E>) Optional.ofNullable(
					mongoTemplate.findById(entity.provideEntityId(), entity.getClass()));

			if (existingEntity.isPresent()) {
				throw new DuplicateKeyException("Entity with ID " + entity.provideEntityId() + " already exists.");
			}

			// Save entity to MongoDB
			return mongoTemplate.save(entity);
		} catch (final DuplicateKeyException e) {
			throw new DuplicateKeyException("Error saving entity: " + e.getMessage());
		}
	}

	public void deleteByIntegerValue(final int value, final String field, final Class<E> clazz) {

		final Query query = new Query();
		query.addCriteria(Criteria.where(field).is(value));

		mongoTemplate.remove(query, clazz);
	}

	// TODO inject Id field name, for now it doesn't work
	// Maybe we can work out something using reflection?
	public Optional<E> findById(final String id, final Class<E> clazz) {

		final Query query = new Query();

		// Build custom criteria
		query.addCriteria(Criteria.where("field1").is(id));

		return Optional.ofNullable(mongoTemplate.find(query, clazz).get(0));
	}

}
