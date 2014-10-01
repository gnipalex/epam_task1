package com.epam.hnyp.task2.subtask3.service;

import java.util.Collection;

import com.epam.hnyp.task2.subtask3.model.Product;

public interface AdvertisementService {
	void put(Product p);
	Collection<Product> getLastProducts();
}
