package com.epam.hnyp.task2.subtask3.repo.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.repo.GoodRepo;

public class GoodRepoInMemory implements GoodRepo {

	private Map<Long, Good> goods = new LinkedHashMap<>();
	
	@Override
	public Good get(long id) {
		return goods.get(id);
	}

	@Override
	public Collection<Good> getAll() {
		return Collections.unmodifiableCollection(goods.values());
	}

	@Override
	public boolean add(Good g) {
		Good good = goods.get(g.getId());
		if (good != null) {
			return false;
		}
		goods.put(g.getId(), g);
		return true;
	}

}
