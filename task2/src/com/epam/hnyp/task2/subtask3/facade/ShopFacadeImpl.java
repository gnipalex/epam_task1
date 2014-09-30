package com.epam.hnyp.task2.subtask3.facade;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.service.ProductsService;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.util.Advertisement;
import com.epam.hnyp.task2.subtask3.util.Cart;

public class ShopFacadeImpl implements ShopFacade {
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
	public Collection<Product> getPopularProducts() {
		return advertisement.getLastProducts();
	}

	@Override
	public int getPriceForCart() {
		return getPriceForItems(cart.getAllItems());
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

	@Override
	public Map<Long, Integer> getCartItems() {
		return cart.getAllItems();
	}

	@Override
	public void clearCart() {
		cart.clear();
	}

	@Override
	public void removeFromCart(Long id) {
		cart.remove(id);
	}

	@Override
	public Collection<Order> getAllOrders() {
		return orderService.getAll();
	}

	@Override
	public Collection<Order> getOrdersOfPeriod(Date from, Date to) {
		return orderService.getOrdersOfPeriod(from, to);
	}

	@Override
	public Order getNearestOrder(Date date) {
		return orderService.getNearest(date);
	}

	@Override
	public Product getProductById(long id) {
		return productsService.get(id);
	}

	@Override
	public Collection<Product> getAllProducts() {
		return productsService.getAll();
	}

	@Override
	public boolean addNewProduct(Product g) {
		return productsService.add(g);
	}

	@Override
	public int getPriceForOrder(Order o) {
		return getPriceForItems(o.getItems());
	}
	
	private int getPriceForItems(Map<Long, Integer> items) {
		int cost = 0;
		for (Entry<Long, Integer> e : items.entrySet()) {
			Product g = getProductById(e.getKey());
			if (g != null) {
				cost += g.getPrice() * e.getValue();
			}
		}
		return cost;
	}

	@Override
	public int cartSize() {
		return cart.size();
	}
}
