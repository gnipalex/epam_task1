package com.epam.hnyp.task2.subtask3.repo.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.repo.ProductRepo;

public class ProductRepoInMemory implements ProductRepo {
	private long lastId;

	private Map<Long, Product> products = new LinkedHashMap<>();
	
	@Override
	public Product get(long id) {
		return products.get(id);
	}

	@Override
	public Collection<Product> getAll() {
		return Collections.unmodifiableCollection(products.values());
	}

	@Override
	public boolean add(Product g) {
		if (g.getId() == 0) {
			//looking for free id
			while(true) {
				g.setId(++lastId);
				Product prod = products.get(g.getId());
				if (prod != null) {
					continue;
				}
				break;
			}
		} else {
			Product prod = products.get(g.getId());
			if (prod != null) {
				return false;
			}
			lastId = g.getId();
		}
		products.put(g.getId(), g);
		return true;
	}

}
