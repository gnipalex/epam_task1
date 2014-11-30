package com.epam.hnyp.shop.service;

import java.util.List;

import com.epam.hnyp.shop.form.ProductFilterBean;
import com.epam.hnyp.shop.model.Category;
import com.epam.hnyp.shop.model.Manufacturer;
import com.epam.hnyp.shop.model.Product;


public interface ProductService {
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	int addProduct(Product p);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	void updateProduct(Product p);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	void removeProduct(int id);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	Product getProduct(int id);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	List<Product> getAllProducts();
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	List<Product> getProductsByFilter(ProductFilterBean filter);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	int getProductsCountWithoutLimit(ProductFilterBean filter);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	List<Manufacturer> getAllManufacturers();
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	List<Category> getAllCategories();
}
