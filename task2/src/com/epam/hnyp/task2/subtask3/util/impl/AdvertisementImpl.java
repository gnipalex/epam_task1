package com.epam.hnyp.task2.subtask3.util.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.Advertisement;

public class AdvertisementImpl implements Advertisement {

	private final int listMaxCount;
	
	/**
	 * Creates advertisement containing last products
	 * @param maxElements
	 * @throws IllegalArgumentException if maxElements < 1
	 */
	public AdvertisementImpl(int maxElements) {
		if (maxElements < 1) {
			throw new IllegalArgumentException();
		}
		this.listMaxCount = maxElements;
	}
	
	//container for last added elements
	private Map<Long, Product> lastProducts = new LinkedHashMap<Long, Product>(
			16, 0.75f, true) {
		@Override
		protected boolean removeEldestEntry(
				java.util.Map.Entry<Long, Product> eldest) {
			return size() > listMaxCount;
		}
	};
	
	@Override
	public void put(Product p) {
		lastProducts.put(p.getId(), p);
	}

	@Override
	public Collection<Product> getLastProducts() {
		return Collections.unmodifiableCollection(lastProducts.values());
	}
	
}
