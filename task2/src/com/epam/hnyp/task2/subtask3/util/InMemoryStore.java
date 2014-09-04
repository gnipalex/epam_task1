package com.epam.hnyp.task2.subtask3.util;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InMemoryStore implements Store {
	private Map<Long, Good> goods = new LinkedHashMap<>();
	{
		long id = 1;
		Good g1 = new Good(id++, "sugar", 12);
		goods.put(g1.getId(), g1);
		Good g2 = new Good(id++, "milk", 11);
		goods.put(g2.getId(), g2);
		Good g3 = new Good(id++, "vafles artek", 30);
		goods.put(g3.getId(), g3);
		Good g4 = new Good(id++, "potato", 4);
		goods.put(g4.getId(), g4);
		Good g5 = new Good(id++, "bread", 5);
		goods.put(g5.getId(), g5);
		Good g6 = new Good(id++, "cola", 10);
		goods.put(g6.getId(), g6);
		Good g7 = new Good(id++, "sigarettes", 20);
		goods.put(g7.getId(), g7);
		Good g8 = new Good(id++, "butter", 15);
		goods.put(g8.getId(), g8);
	}
	
	private InMemoryStore() {
	}
	
	private static InMemoryStore singleTone;
	
	public static synchronized InMemoryStore getInstance() {
		if (singleTone == null) {
			singleTone = new InMemoryStore();
		}
		return singleTone;
	}

	@Override
	public Good get(long id) {
		return goods.get(id);
	}
	
	@Override
	public Collection<Good> getAll() {
		return Collections.unmodifiableCollection(goods.values());
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

}
