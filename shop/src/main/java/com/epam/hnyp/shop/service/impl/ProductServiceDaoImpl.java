package com.epam.hnyp.shop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.epam.hnyp.shop.dao.CategoryDao;
import com.epam.hnyp.shop.dao.ManufacturerDao;
import com.epam.hnyp.shop.dao.ProductDao;
import com.epam.hnyp.shop.form.ProductFilterBean;
import com.epam.hnyp.shop.model.Category;
import com.epam.hnyp.shop.model.Manufacturer;
import com.epam.hnyp.shop.model.Product;
import com.epam.hnyp.shop.service.ProductService;

public class ProductServiceDaoImpl implements ProductService {
	private TransactionManager transactionManager;
	private ProductDao productDao;
	private CategoryDao categoryDao;
	private ManufacturerDao manufacturerDao;
	
	public ProductServiceDaoImpl(TransactionManager transactionManager,
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
	public List<Product> getAllProducts() {
		return transactionManager.doInTransaction(new ITransactedOperation<List<Product>>() {
			@Override
			public List<Product> execute(Connection con)
					throws SQLException {
				List<Product> items = productDao.getAll(con);
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
	public List<Product> getProductsByFilter(final ProductFilterBean filter) {
		return transactionManager.doInTransaction(new ITransactedOperation<List<Product>>() {
			@Override
			public List<Product> execute(Connection con)
					throws SQLException {
				List<Product> items = productDao.getByFilter(filter, con);
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
	public List<Manufacturer> getAllManufacturers() {
		return transactionManager.doInTransaction(new ITransactedOperation<List<Manufacturer>>() {
			@Override
			public List<Manufacturer> execute(Connection con)
					throws SQLException {
				return manufacturerDao.getAll(con);
			}
		});
	}

	@Override
	public List<Category> getAllCategories() {
		return transactionManager.doInTransaction(new ITransactedOperation<List<Category>>() {
			@Override
			public List<Category> execute(Connection con)
					throws SQLException {
				return categoryDao.getAll(con);
			}
		});
	}
}
