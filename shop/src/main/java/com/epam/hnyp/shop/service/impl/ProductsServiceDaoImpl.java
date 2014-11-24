package com.epam.hnyp.shop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.shop.dao.CategoryDao;
import com.epam.hnyp.shop.dao.ManufacturerDao;
import com.epam.hnyp.shop.dao.ProductDao;
import com.epam.hnyp.shop.form.ProductFilterBean;
import com.epam.hnyp.shop.model.Category;
import com.epam.hnyp.shop.model.Manufacturer;
import com.epam.hnyp.shop.model.Product;
import com.epam.hnyp.shop.service.ProductsService;

public class ProductsServiceDaoImpl implements ProductsService {
	private TransactionManager transactionManager;
	private ProductDao productDao;
	private CategoryDao categoryDao;
	private ManufacturerDao manufacturerDao;
	
	public ProductsServiceDaoImpl(TransactionManager transactionManager,
			ProductDao productDao, CategoryDao categoryDao,
			ManufacturerDao manufacturerDao) {
		this.transactionManager = transactionManager;
		this.productDao = productDao;
		this.categoryDao = categoryDao;
		this.manufacturerDao = manufacturerDao;
	}

	@Override
	public int addProduct(final Product p) {
		return transactionManager.doInTransaction(new ITransactedOperation<Integer>() {
		 	@Override
		 	public Integer execute(Connection con) throws SQLException {
		 		return productDao.add(p, con);
		 	}
		});
	}

	@Override
	public void updateProduct(final Product p) {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				productDao.update(p, con);
				return null;
			}
		});
		
	}

	@Override
	public void removeProduct(final int id) {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				productDao.remove(id, con);
				return null;
			}
		});		
	}

	@Override
	public Product getProduct(final int id) {
		return transactionManager.doInTransaction(new ITransactedOperation<Product>() {
			@Override
			public Product execute(Connection con) throws SQLException {
				Product p = productDao.get(id, con);
				initProduct(p, con);
				return p;
			}
		});
	}
	
	private void initProduct(Product p, Connection con) throws SQLException {
		if (p != null) {
			Category cat = categoryDao.get(p.getCategoryId(), con);
			p.setCategory(cat);
			Manufacturer m = manufacturerDao.get(p.getManufacturerId(), con);
			p.setManufacturer(m);
		}
	}

	@Override
	public Collection<Product> getAllProducts() {
		return transactionManager.doInTransaction(new ITransactedOperation<Collection<Product>>() {
			@Override
			public Collection<Product> execute(Connection con)
					throws SQLException {
				Collection<Product> items = productDao.getAll(con);
				if (items != null && !items.isEmpty()){
					for (Product p : items) {
						initProduct(p, con);
					}
				}
				return items;
			}
		});
	}

	@Override
	public Collection<Product> getProductsByFilter(final ProductFilterBean filter) {
		return transactionManager.doInTransaction(new ITransactedOperation<Collection<Product>>() {
			@Override
			public Collection<Product> execute(Connection con)
					throws SQLException {
				Collection<Product> items = productDao.getByFilter(filter, con);
				if (items != null && !items.isEmpty()){
					for (Product p : items) {
						initProduct(p, con);
					}
				}
				return items;
			}
		});
	}

	@Override
	public int getProductsCountWithoutLimit(final ProductFilterBean filter) {
		return transactionManager.doInTransaction(new ITransactedOperation<Integer>() {
			@Override
			public Integer execute(Connection con)
					throws SQLException {
				return productDao.getCountWithoutLimit(filter, con);
			}
		});
	}

	@Override
	public Collection<Manufacturer> getAllManufacturers() {
		return transactionManager.doInTransaction(new ITransactedOperation<Collection<Manufacturer>>() {
			@Override
			public Collection<Manufacturer> execute(Connection con)
					throws SQLException {
				return manufacturerDao.getAll(con);
			}
		});
	}

	@Override
	public Collection<Category> getAllCategories() {
		return transactionManager.doInTransaction(new ITransactedOperation<Collection<Category>>() {
			@Override
			public Collection<Category> execute(Connection con)
					throws SQLException {
				return categoryDao.getAll(con);
			}
		});
	}
}
