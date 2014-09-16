package com.epam.hnyp.task2.subtask3.repo.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.repo.GoodRepo;

public class GoodRepoInMemory implements GoodRepo {
	private long lastId;

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
		if (g.getId() == 0) {
			//looking for free id
			while(true) {
				g.setId(++lastId);
				Good good = goods.get(g.getId());
				if (good != null) {
					continue;
				}
				break;
			}
		} else {
			Good good = goods.get(g.getId());
			if (good != null) {
				return false;
			}
			lastId = g.getId();
		}
		goods.put(g.getId(), g);
		return true;
	}

}
