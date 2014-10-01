package com.epam.hnyp.task2.subtask3.initializer;

import com.epam.hnyp.task2.subtask3.facade.ShopFacade;
import com.epam.hnyp.task2.subtask3.service.OrderService;
import com.epam.hnyp.task2.subtask3.service.ProductsService;

public interface ServicesInitializer {
	/**
	 * 
	 * @param advMax max count of elements of advertisement
	 * @return
	 */
	ServicesContainer buildServicesContainer(int advMax);
	
	public static class ServicesContainer {
		private ShopFacade shopFacade;
		private OrderService orderService;
		private ProductsService productsService;
		
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
		public ShopFacade getShopFacade() {
			return shopFacade;
		}
		public void setShopFacade(ShopFacade shopFacade) {
			this.shopFacade = shopFacade;
		}
	}
}
