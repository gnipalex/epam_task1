package com.epam.hnyp.task2.subtask3.factory;

import com.epam.hnyp.task2.subtask3.service.GoodsService;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.service.ShopService;

public class ServicesContainer {
	private ShopService shopService;
	private OrderService orderService;
	private GoodsService goodsService;
	
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
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
}
