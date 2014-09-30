package com.epam.hnyp.task2.subtask3.factory;

import com.epam.hnyp.task2.subtask3.facade.ShopFacadeImpl;
import com.epam.hnyp.task2.subtask3.repo.ProductRepo;
import com.epam.hnyp.task2.subtask3.repo.OrderRepo;
import com.epam.hnyp.task2.subtask3.repo.impl.ProductRepoInMemory;
import com.epam.hnyp.task2.subtask3.repo.impl.OrderRepoInMemory;
import com.epam.hnyp.task2.subtask3.service.impl.ProductsServiceImpl;
import com.epam.hnyp.task2.subtask3.service.impl.OrderServiceImpl;
import com.epam.hnyp.task2.subtask3.util.impl.AdvertisementImpl;
import com.epam.hnyp.task2.subtask3.util.impl.CartImpl;

public class ServicesInMemoryInitializer implements ServicesInitializer {
	
	@Override
	public ServicesContainer buildServicesContainer(int advMax) {
		ProductRepo productRepo = new ProductRepoInMemory();
		OrderRepo orderRepo = new OrderRepoInMemory();
		
		ProductsServiceImpl productService = new ProductsServiceImpl();
		productService.setGoodRepo(productRepo);
		
		OrderServiceImpl orderService = new OrderServiceImpl();
		orderService.setOrderRepo(orderRepo);
		
		ShopFacadeImpl shopFacade = new ShopFacadeImpl();
		shopFacade.setAdvertisement(new AdvertisementImpl(advMax));
		shopFacade.setCart(new CartImpl());
		shopFacade.setProductsService(productService);
		shopFacade.setOrderService(orderService);
		
		ServicesContainer container = new ServicesContainer();
		container.setProductsService(productService);
		container.setOrderService(orderService);
		container.setShopFacade(shopFacade);
		
		return container;
	}

}
