package com.epam.hnyp.task2.subtask3.util;

import java.util.Collection;

import com.epam.hnyp.task2.subtask3.model.Product;

public interface Advertisement {
	void put(Product p);
	Collection<Product> getLastProducts();
}
