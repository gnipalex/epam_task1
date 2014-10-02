package com.epam.hnyp.task2.subtask3.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.repo.OrderRepo;
import com.epam.hnyp.task2.subtask3.service.CartService;
import com.epam.hnyp.task2.subtask3.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderRepo orderRepo;
	
	@Override
	public Collection<Order> getAll() {
		return orderRepo.getAll();
	}

	@Override
	public Collection<Order> getOrdersOfPeriod(Date from, Date to) {
		return orderRepo.getOrdersOfPeriod(from, to);
	}

	@Override
	public Order getNearest(Date date) {
		return orderRepo.getNearest(date);
	}

	@Override
	public boolean makeOrder(CartService cart, String customer, Date date) {
		Order o = new Order(date, customer, new HashMap<>(cart.getAllItems()));
		return orderRepo.add(o);
	}

	public OrderRepo getOrderRepo() {
		return orderRepo;
	}

	public void setOrderRepo(OrderRepo orderRepo) {
		this.orderRepo = orderRepo;
	}

}
