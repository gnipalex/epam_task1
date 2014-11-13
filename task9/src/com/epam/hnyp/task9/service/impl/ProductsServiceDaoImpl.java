package com.epam.hnyp.task9.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.task9.dao.CategoryDao;
import com.epam.hnyp.task9.dao.ManufacturerDao;
import com.epam.hnyp.task9.dao.ProductDao;
import com.epam.hnyp.task9.form.ProductFilterBean;
import com.epam.hnyp.task9.model.Category;
import com.epam.hnyp.task9.model.Manufacturer;
import com.epam.hnyp.task9.model.Product;
import com.epam.hnyp.task9.service.ProductsService;

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
				return productDao.get(id, con);
			}
		});
	}

	@Override
	public Collection<Product> getAllProducts() {
		return transactionManager.doInTransaction(new ITransactedOperation<Collection<Product>>() {
			@Override
			public Collection<Product> execute(Connection con)
					throws SQLException {
				return productDao.getAll(con);
			}
		});
	}

	@Override
	public Collection<Product> getProductsByFilter(final ProductFilterBean filter) {
		return transactionManager.doInTransaction(new ITransactedOperation<Collection<Product>>() {
			@Override
			public Collection<Product> execute(Connection con)
					throws SQLException {
				return productDao.getByFilter(filter, con);
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
