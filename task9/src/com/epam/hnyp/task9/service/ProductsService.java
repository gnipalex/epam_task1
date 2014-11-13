package com.epam.hnyp.task9.service;

import java.util.Collection;

import com.epam.hnyp.task9.form.ProductFilterBean;
import com.epam.hnyp.task9.model.Category;
import com.epam.hnyp.task9.model.Manufacturer;
import com.epam.hnyp.task9.model.Product;


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
