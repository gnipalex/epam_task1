package com.epam.hnyp.task2.subtask3.factory;

import com.epam.hnyp.task2.subtask3.repo.GoodRepo;
import com.epam.hnyp.task2.subtask3.repo.OrderRepo;
import com.epam.hnyp.task2.subtask3.repo.impl.GoodRepoInMemory;
import com.epam.hnyp.task2.subtask3.repo.impl.OrderRepoInMemory;
import com.epam.hnyp.task2.subtask3.service.impl.GoodsServiceImpl;
import com.epam.hnyp.task2.subtask3.service.impl.OrderServiceImpl;
import com.epam.hnyp.task2.subtask3.service.impl.ShopServiceImpl;
import com.epam.hnyp.task2.subtask3.util.impl.AdvertisementImpl;
import com.epam.hnyp.task2.subtask3.util.impl.CartImpl;

public class ServicesFactoryInMemory implements ServicesFactory {
	
	@Override
	public ServicesContainer buildServicesContainer(int advMax) {
		GoodRepo goodRepo = new GoodRepoInMemory();
		OrderRepo orderRepo = new OrderRepoInMemory();
		
		GoodsServiceImpl goodService = new GoodsServiceImpl();
		goodService.setGoodRepo(goodRepo);
		
		OrderServiceImpl orderService = new OrderServiceImpl();
		orderService.setOrderRepo(orderRepo);
		
		ShopServiceImpl shopService = new ShopServiceImpl();
		shopService.setAdvertisement(new AdvertisementImpl(advMax));
		shopService.setCart(new CartImpl());
		shopService.setGoodsService(goodService);
		shopService.setOrderService(orderService);
		
		ServicesContainer container = new ServicesContainer();
		container.setGoodsService(goodService);
		container.setOrderService(orderService);
		container.setShopService(shopService);
		
		return container;
	}

}
