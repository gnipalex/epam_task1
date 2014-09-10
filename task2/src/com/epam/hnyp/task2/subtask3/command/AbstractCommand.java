package com.epam.hnyp.task2.subtask3.command;

import com.epam.hnyp.task2.subtask3.factory.ServicesContainer;
import com.epam.hnyp.task2.subtask3.service.GoodsService;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.service.ShopService;

public abstract class AbstractCommand {

	public static ServicesContainer services;
	
	public abstract void execute(String ...args);
	
	public abstract String about();
	
	public static class WrongInputArgumentsException extends Exception {
		public WrongInputArgumentsException() {
		}
		
		public WrongInputArgumentsException(String message) {
			super(message);
		}
	}
	
	protected ShopService getShopService() {
		return services.getShopService();
	}
	
	protected OrderService getOrderService() {
		return services.getOrderService();
	}
	
	protected GoodsService getGoodsService() {
		return services.getGoodsService();
	}
}
