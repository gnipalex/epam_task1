package com.epam.hnyp.task2.subtask3.service.impl;

import java.util.Collection;
import java.util.Date;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.service.ProductsService;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.service.ShopService;
import com.epam.hnyp.task2.subtask3.util.Advertisement;
import com.epam.hnyp.task2.subtask3.util.Cart;

public class ShopServiceImpl implements ShopService {
	private Cart cart;
	private Advertisement advertisement;
	private OrderService orderService;
	private ProductsService productsService;
	
	@Override
	public boolean addToCart(long id) {
		Product g = productsService.get(id);
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
	public Collection<Product> getPopularProducts() {
		return advertisement.getLastProducts();
	}

	@Override
	public int getPriceForCart() {
		return productsService.getPriceForAll(cart.getAllItems());
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

	public ProductsService getProductsService() {
		return productsService;
	}

	public void setProductsService(ProductsService goodsService) {
		this.productsService = goodsService;
	}
}
