package com.epam.hnyp.task2.subtask3.service.impl;

import java.util.Collection;
import java.util.Date;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.service.GoodsService;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.service.ShopService;
import com.epam.hnyp.task2.subtask3.util.Advertisement;
import com.epam.hnyp.task2.subtask3.util.Cart;

public class ShopServiceImpl implements ShopService {
	private Cart cart;
	private Advertisement advertisement;
	private OrderService orderService;
	private GoodsService goodsService;
	
	@Override
	public boolean addToCart(long id) {
		Good g = goodsService.get(id);
		if (g == null) {
			return false;
		}
		cart.add(g);
		return true;
	}

	@Override
	public boolean makeOrder(String customer, Date date) {
		if (cart.size() == 0) {
			return false;
		}
		boolean res = orderService.makeOrder(cart, customer, date);
		if (res) {
			cart.clear();
		}
		return res;
	}

	@Override
	public Cart getCurrentCart() {
		return cart;
	}

	@Override
	public Collection<Good> getPopularGoods() {
		return advertisement.getLastGoods();
	}

	@Override
	public int getPriceForCart() {
		return goodsService.getPriceForAll(cart.getAllItems());
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
}
