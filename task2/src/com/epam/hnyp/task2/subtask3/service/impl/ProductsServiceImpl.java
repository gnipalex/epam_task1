package com.epam.hnyp.task2.subtask3.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.repo.ProductRepo;
import com.epam.hnyp.task2.subtask3.service.ProductsService;

public class ProductsServiceImpl implements ProductsService {
	private ProductRepo goodRepo;
	
	@Override
	public Product get(long id) {
		return goodRepo.get(id);
	}

	@Override
	public Collection<Product> getAll() {
		return goodRepo.getAll();
	}

	@Override
	public boolean add(Product g) {
		return goodRepo.add(g);
	}

//	@Override
//	public int getPriceForAll(Map<Long, Integer> items) {
//		int cost = 0;
//		for (Entry<Long, Integer> e : items.entrySet()) {
//			Product g = get(e.getKey());
//			if (g != null) {
//				cost += g.getPrice() * e.getValue();
//			}
//		}
//		return cost;
//	}
	
	public ProductRepo getGoodRepo() {
		return goodRepo;
	}
	
	public void setGoodRepo(ProductRepo goodRepo) {
		this.goodRepo = goodRepo;
	}
}
