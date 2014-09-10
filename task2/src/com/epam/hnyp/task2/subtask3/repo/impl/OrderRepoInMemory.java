package com.epam.hnyp.task2.subtask3.repo.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.repo.OrderRepo;

public class OrderRepoInMemory implements OrderRepo {

	private NavigableMap<Date, Order> orders = new TreeMap<>();
	
	@Override
	public boolean add(Order order) {
		Order o = orders.get(order.getDate());
		if (o != null) {
			return false;
		}
		orders.put(order.getDate(), order);
		return true;
	}

	@Override
	public Collection<Order> getAll() {
		return Collections.unmodifiableCollection(orders.values());
	}

	@Override
	public Collection<Order> getOrdersOfPeriod(Date from, Date to) {
		return Collections.unmodifiableCollection(orders.subMap(from, to).values());
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
