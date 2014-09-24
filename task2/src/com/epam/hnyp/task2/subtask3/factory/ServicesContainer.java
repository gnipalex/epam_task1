package com.epam.hnyp.task2.subtask3.factory;

import com.epam.hnyp.task2.subtask3.service.ProductsService;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.service.ShopService;

public class ServicesContainer {
	private ShopService shopService;
	private OrderService orderService;
	private ProductsService productsService;
	
	public ShopService getShopService() {
		return shopService;
	}
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
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
	public void setProductsService(ProductsService productsService) {
		this.productsService = productsService;
	}
}
