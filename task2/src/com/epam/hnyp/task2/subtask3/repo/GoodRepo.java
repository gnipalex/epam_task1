package com.epam.hnyp.task2.subtask3.repo;

import java.util.Collection;

import com.epam.hnyp.task2.subtask3.model.Good;

public interface GoodRepo {
	/**
	 * Gets good from the store by id. If good doesn't present in the store returns null.
	 * @param id
	 * @return
	 */
	Good get(long id);
	/**
	 * Returns collection of all goods from the store
	 * @return Collection
	 */
	Collection<Good> getAll();
	/**
	 * Adds new good to the store. If id == 0 generates id
	 * @param g
	 * @return true if good was added
	 */
	boolean add(Good g);
}
