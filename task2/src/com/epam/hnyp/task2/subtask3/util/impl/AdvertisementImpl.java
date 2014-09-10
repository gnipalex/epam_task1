package com.epam.hnyp.task2.subtask3.util.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.util.Advertisement;

public class AdvertisementImpl implements Advertisement {

	private final int listMaxCount;
	
	/**
	 * Creates advertisement containing last goods
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
	private Map<Long, Good> lastGoods = new LinkedHashMap<Long, Good>(
			16, 0.75f, true) {
		@Override
		protected boolean removeEldestEntry(
				java.util.Map.Entry<Long, Good> eldest) {
			return size() > listMaxCount;
		}
	};
	
	@Override
	public void put(Good g) {
		lastGoods.put(g.getId(), g);
	}

	@Override
	public Collection<Good> getLastGoods() {
		return Collections.unmodifiableCollection(lastGoods.values());
	}
	
}
