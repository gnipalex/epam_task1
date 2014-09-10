package com.epam.hnyp.task2.subtask3.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.epam.hnyp.task2.subtask3.model.CerealGood;
import com.epam.hnyp.task2.subtask3.model.DrinkGood;
import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.model.SweetGood;
import com.epam.hnyp.task2.subtask3.model.VegetableGood;
import com.epam.hnyp.task2.subtask3.model.WeightableGood;

public class InMemoryStore implements Store {
	private Map<Long, Good> goods = new LinkedHashMap<>();
	{
		long id = 1;
		Good g1 = new CerealGood(id++, "sugar", 12, 2);
		goods.put(g1.getId(), g1);
		Good g2 = new DrinkGood(id++, "milk", 11, 1);
		goods.put(g2.getId(), g2);
		Good g3 = new SweetGood(id++, "vafles artek", 30, 0.2, "chocolate");
		goods.put(g3.getId(), g3);
		Good g4 = new VegetableGood(id++, "potato", 4, 1);
		goods.put(g4.getId(), g4);
		Good g5 = new WeightableGood(id++, "bread", 5, 0.7);
		goods.put(g5.getId(), g5);
		Good g6 = new DrinkGood(id++, "cola", 12, 2);
		goods.put(g6.getId(), g6);
		Good g7 = new Good(id++, "sigarettes", 20);
		goods.put(g7.getId(), g7);
		Good g8 = new Good(id++, "butter", 15);
		goods.put(g8.getId(), g8);
	}

	private NavigableMap<Date, Order> orders = new TreeMap<>();

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

	@Override
	public boolean saveOrder(Order order) {
		orders.put(order.getDate(), order);
		return true;
	}

	@Override
	public Collection<Order> getAllOrders() {
		return orders.values();
	}

	@Override
	public Collection<Order> getOrdersOfPeriod(Date from, Date to) {
		return orders.subMap(from, to).values();
	}

	@Override
	public Order getNearest(Date date) {
		Date up_key = orders.ceilingKey(date);
		Date down_key = orders.floorKey(date);
		if (up_key == null && down_key == null) {
			return null;
		}
		if (up_key == null) {
			return orders.get(down_key);
		}
		if (down_key == null) {
			return orders.get(up_key);
		}
		Long up_time = up_key.getTime();
		Long down_time = down_key.getTime();
		Long date_time = date.getTime();
		Order order = null;
		
		if (up_time - date_time <= date_time - down_time) {
			order = orders.get(up_key);
		} else {
			order = orders.get(down_key);
		}
		return order;
	}


}
