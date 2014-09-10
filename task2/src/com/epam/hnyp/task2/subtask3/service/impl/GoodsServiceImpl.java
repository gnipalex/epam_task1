package com.epam.hnyp.task2.subtask3.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.repo.GoodRepo;
import com.epam.hnyp.task2.subtask3.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	private GoodRepo goodRepo;
	
	@Override
	public Good get(long id) {
		return goodRepo.get(id);
	}

	@Override
	public Collection<Good> getAll() {
		return goodRepo.getAll();
	}

	@Override
	public boolean add(Good g) {
		return goodRepo.add(g);
	}

	@Override
	public int getPriceForAll(Map<Long, Integer> items) {
		int cost = 0;
		for (Entry<Long, Integer> e : items.entrySet()) {
			Good g = get(e.getKey());
			if (g != null) {
				cost += g.getPrice() * e.getValue();
			}
		}
		return cost;
	}
	
	public GoodRepo getGoodRepo() {
		return goodRepo;
	}
	
	public void setGoodRepo(GoodRepo goodRepo) {
		this.goodRepo = goodRepo;
	}
}
