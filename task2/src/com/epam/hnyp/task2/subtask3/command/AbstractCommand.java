package com.epam.hnyp.task2.subtask3.command;

import com.epam.hnyp.task2.subtask3.factory.ServicesContainer;
import com.epam.hnyp.task2.subtask3.service.ProductsService;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.service.ShopService;

public abstract class AbstractCommand {

	public static ServicesContainer services;
	
	public abstract void execute(String ...args);
	
	public abstract String about();
	
	protected ShopService getShopService() {
		return services.getShopService();
	}
	
	protected OrderService getOrderService() {
		return services.getOrderService();
	}
	
	protected ProductsService getProductsService() {
		return services.getProductsService();
	}
}
