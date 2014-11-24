package com.epam.hnyp.shop.service;

import java.util.Collection;

import com.epam.hnyp.shop.form.ProductFilterBean;
import com.epam.hnyp.shop.model.Category;
import com.epam.hnyp.shop.model.Manufacturer;
import com.epam.hnyp.shop.model.Product;


public interface ProductsService {
	int addProduct(Product p);
	void updateProduct(Product p);
	void removeProduct(int id);
	Product getProduct(int id);
	Collection<Product> getAllProducts();
	Collection<Product> getProductsByFilter(ProductFilterBean filter);
	int getProductsCountWithoutLimit(ProductFilterBean filter);
	Collection<Manufacturer> getAllManufacturers();
	Collection<Category> getAllCategories();
}
