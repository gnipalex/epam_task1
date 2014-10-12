package com.epam.hnyp.task7.subtask1.facade;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.service.ProductsService;

public class ProductsShopFacade implements ProductsFacade {

	private ProductsService prodService;
	
	@Override
	public int getCount() {
		return prodService.getAll().size();
	}

	@Override
	public ProductInfo getProductInfo(long id) {
		Product prod = prodService.get(id);
		if (prod != null) {
			return new ProductInfo(prod.getName(), prod.getPrice());
		}
		return null;
	}

	public ProductsService getProdService() {
		return prodService;
	}

	public void setProdService(ProductsService prodService) {
		this.prodService = prodService;
	}
}
