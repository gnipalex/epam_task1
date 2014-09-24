package com.epam.hnyp.task2.subtask3.factory;

import com.epam.hnyp.task2.subtask3.repo.ProductRepo;
import com.epam.hnyp.task2.subtask3.repo.OrderRepo;
import com.epam.hnyp.task2.subtask3.repo.impl.ProductRepoInMemory;
import com.epam.hnyp.task2.subtask3.repo.impl.OrderRepoInMemory;
import com.epam.hnyp.task2.subtask3.service.impl.ProductsServiceImpl;
import com.epam.hnyp.task2.subtask3.service.impl.OrderServiceImpl;
import com.epam.hnyp.task2.subtask3.service.impl.ShopServiceImpl;
import com.epam.hnyp.task2.subtask3.util.impl.AdvertisementImpl;
import com.epam.hnyp.task2.subtask3.util.impl.CartImpl;

public class ServicesFactoryInMemory implements ServicesFactory {
	
	@Override
	public ServicesContainer buildServicesContainer(int advMax) {
		ProductRepo productRepo = new ProductRepoInMemory();
		OrderRepo orderRepo = new OrderRepoInMemory();
		
		ProductsServiceImpl productService = new ProductsServiceImpl();
		productService.setGoodRepo(productRepo);
		
		OrderServiceImpl orderService = new OrderServiceImpl();
		orderService.setOrderRepo(orderRepo);
		
		ShopServiceImpl shopService = new ShopServiceImpl();
		shopService.setAdvertisement(new AdvertisementImpl(advMax));
		shopService.setCart(new CartImpl());
		shopService.setProductsService(productService);
		shopService.setOrderService(orderService);
		
		ServicesContainer container = new ServicesContainer();
		container.setProductsService(productService);
		container.setOrderService(orderService);
		container.setShopService(shopService);
		
		return container;
	}

}
