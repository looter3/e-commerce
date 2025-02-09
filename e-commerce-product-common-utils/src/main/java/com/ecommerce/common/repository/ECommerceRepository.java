package com.ecommerce.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecommerce.common.entity.ECommerceEntity;

/**
 * @author Lorenzo Leccese
 *
 *         7 feb 2025
 *
 */
public interface ECommerceRepository<E extends ECommerceEntity>
		extends PagingAndSortingRepository<E, String>, CrudRepository<E, String> {

}
