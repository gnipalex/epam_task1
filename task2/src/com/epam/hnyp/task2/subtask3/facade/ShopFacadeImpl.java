package com.epam.hnyp.task2.subtask3.facade;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.service.AdvertisementService;
import com.epam.hnyp.task2.subtask3.service.CartService;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.service.ProductsService;

public class ShopFacadeImpl implements ShopFacade {
	private CartService cartService;
	private AdvertisementService advertisementService;
	private OrderService orderService;
	private ProductsService productsService;
	
	@Override
	public boolean addToCart(long id) {
		Product g = productsService.get(id);
		if (g == null) {
			return false;
		}
		cartService.add(g);
		advertisementService.put(g);
		return true;
	}

	@Override
	public boolean makeOrder(String customer, Date date) {
		if (cartService.size() == 0) {
			return false;
		}
		boolean res = orderService.makeOrder(cartService, customer, date);
		if (res) {
			cartService.clear();
		}
		return res;
	}

	@Override
	public Collection<Product> getPopularProducts() {
		return advertisementService.getLastProducts();
	}

	@Override
	public int getPriceForCart() {
		return getPriceForItems(cartService.getAllItems());
	}

	@Override
	public Map<Long, Integer> getCartItems() {
		return cartService.getAllItems();
	}

	@Override
	public void clearCart() {
		cartService.clear();
	}
	
	@Override
	public int cartSize() {
		return cartService.size();
	}

	@Override
	public void removeFromCart(Long id) {
		cartService.remove(id);
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

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cart) {
		this.cartService = cart;
	}

	public AdvertisementService getAdvertisementService() {
		return advertisementService;
	}

	public void setAdvertisementService(AdvertisementService advertisement) {
		this.advertisementService = advertisement;
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
