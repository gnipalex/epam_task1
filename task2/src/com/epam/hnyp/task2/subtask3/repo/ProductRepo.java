package com.epam.hnyp.task2.subtask3.repo;

import java.util.Collection;

import com.epam.hnyp.task2.subtask3.model.Product;

public interface ProductRepo {
	/**
	 * Gets product from the store by id. If product doesn't present in the store returns null.
	 * @param id
	 * @return
	 */
	Product get(long id);
	/**
	 * Returns collection of all products from the store
	 * @return Collection
	 */
	Collection<Product> getAll();
	/**
	 * Adds new product to the store
	 * @param g
	 * @return true if product was added
	 */
	boolean add(Product g);
}
