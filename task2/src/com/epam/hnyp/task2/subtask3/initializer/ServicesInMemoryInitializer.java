package com.epam.hnyp.task2.subtask3.initializer;

import com.epam.hnyp.task2.subtask3.facade.impl.ShopFacadeImpl;
import com.epam.hnyp.task2.subtask3.repo.ProductRepo;
import com.epam.hnyp.task2.subtask3.repo.OrderRepo;
import com.epam.hnyp.task2.subtask3.repo.impl.ProductRepoInMemory;
import com.epam.hnyp.task2.subtask3.repo.impl.OrderRepoInMemory;
import com.epam.hnyp.task2.subtask3.service.impl.AdvertisementServiceImpl;
import com.epam.hnyp.task2.subtask3.service.impl.CartServiceImpl;
import com.epam.hnyp.task2.subtask3.service.impl.ProductsServiceImpl;
import com.epam.hnyp.task2.subtask3.service.impl.OrderServiceImpl;

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
		shopFacade.setAdvertisementService(new AdvertisementServiceImpl(advMax));
		shopFacade.setCartService(new CartServiceImpl());
		shopFacade.setProductsService(productService);
		shopFacade.setOrderService(orderService);
		
		ServicesContainer container = new ServicesContainer();
		container.setProductsService(productService);
		container.setOrderService(orderService);
		container.setShopFacade(shopFacade);
		
		return container;
	}

}
